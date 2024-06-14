package one.bca.employee_attendance_report_batch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.bca.employee_attendance_report_batch.AppConfigurationProperties;
import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceReportDto;
import one.bca.employee_attendance_report_batch.enumHelper.AttendanceStatusEnum;
import one.bca.employee_attendance_report_batch.mapper.AttendanceRowMapper;
import one.bca.employee_attendance_report_batch.model.Attendance;
import one.bca.employee_attendance_report_batch.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EmployeeAttendanceProcessor implements ItemProcessor<Employee, EmployeeAttendanceReportDto> {
    private final JdbcTemplate jdbcTemplate;
    private final AppConfigurationProperties configuration;

    private static final String GET_ATTENDANCE_SQL = "select " +
            "ea.employee_id, ea.date, ea.clock_in, ea.clock_out, ea.attendance_status, ea.overtime_status, ea.overtime_start, ea.overtime_end " +
            "from EMPLOYEE_ATTENDANCE ea " +
            "where ea.employee_id = ? " +
            "order by ea.date asc";

    @Override
    public EmployeeAttendanceReportDto process(Employee employee) {
        /*
            Step:
            1. calculate
            2. write report to csv (format: EmployeeAttendanceReportDto)
            3. update past and current leave in employee table
         */

        log.info("Processing employee: {}", employee.getEmployeeId());

        List<Attendance> attendanceList = jdbcTemplate.query(GET_ATTENDANCE_SQL, new AttendanceRowMapper(), employee.getEmployeeId());

        int totalAttendDays = (int) attendanceList
                .stream()
                .filter(x -> x.getAttendanceStatus() == AttendanceStatusEnum.WORK && x.getClockIn().before(configuration.getAttendanceClockIn()) && x.getClockOut().after(configuration.getAttendanceClockOut()))
                .count();

        int totalLateDays = (int) attendanceList
                .stream()
                .filter(x -> x.getAttendanceStatus() == AttendanceStatusEnum.WORK && x.getClockIn().after(configuration.getAttendanceClockIn()))
                .count();

        AtomicInteger totalOvertimeHours = new AtomicInteger();

        attendanceList
                .stream()
                .filter(x -> x.getAttendanceStatus() == AttendanceStatusEnum.WORK && x.getClockOut().after(configuration.getStartOvertimeHour()))
                .forEach(x ->
                        totalOvertimeHours.addAndGet((int) Duration.between(x.getClockOut().toLocalTime(), Time.valueOf("23:59:59").toLocalTime()).toHours())
                );

        int totalPaidLeaveDays = (int) attendanceList
                .stream()
                .filter(x -> x.getAttendanceStatus() == AttendanceStatusEnum.PAID_LEAVE)
                .count();

        int paidLeaveLimitRemaining = employee.getPaidLeaveLimit() - employee.getUsedPaidLeave() - totalPaidLeaveDays;

        return new EmployeeAttendanceReportDto(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getCategory(),
                employee.getDivision(),
                totalAttendDays,
                totalLateDays,
                totalOvertimeHours.get(),
                totalPaidLeaveDays,
                paidLeaveLimitRemaining
        );
    }
}
