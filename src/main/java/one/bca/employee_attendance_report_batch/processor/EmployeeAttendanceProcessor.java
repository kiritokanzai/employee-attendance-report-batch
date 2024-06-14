package one.bca.employee_attendance_report_batch.processor;

import lombok.extern.slf4j.Slf4j;
import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceDataDto;
import one.bca.employee_attendance_report_batch.mapper.AttendanceRowMapper;
import one.bca.employee_attendance_report_batch.model.Attendance;
import one.bca.employee_attendance_report_batch.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
@Slf4j
public class EmployeeAttendanceProcessor implements ItemProcessor<Employee, EmployeeAttendanceDataDto> {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeAttendanceProcessor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_ATTENDANCE_SQL = "select " +
            "ea.employee_id, ea.date, ea.clock_in, ea.clock_out, ea.attendance_status, ea.overtime_status, ea.overtime_start, ea.overtime_end " +
            "from EMPLOYEE_ATTENDANCE ea " +
            "where ea.employee_id = ? " +
            "order by ea.date asc";

    @Override
    public EmployeeAttendanceDataDto process(Employee item) {
        log.info("Processing employee: {}", item.getEmployeeId());

        List<Attendance> attendanceList = jdbcTemplate.query(GET_ATTENDANCE_SQL, new AttendanceRowMapper(), item.getEmployeeId());

        return new EmployeeAttendanceDataDto(item, attendanceList);
    }
}
