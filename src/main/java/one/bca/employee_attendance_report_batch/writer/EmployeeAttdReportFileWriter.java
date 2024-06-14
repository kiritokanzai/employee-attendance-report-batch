package one.bca.employee_attendance_report_batch.writer;

import com.opencsv.CSVWriter;
import one.bca.employee_attendance_report_batch.AppConfigurationProperties;
import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceDataDto;
import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceReportDto;
import one.bca.employee_attendance_report_batch.enumHelper.AttendanceStatusEnum;
import one.bca.employee_attendance_report_batch.model.Attendance;
import one.bca.employee_attendance_report_batch.model.Employee;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Configuration
public class EmployeeAttdReportFileWriter implements ItemWriter<EmployeeAttendanceDataDto> {
    private final JdbcTemplate jdbcTemplate;
    private final AppConfigurationProperties configuration;

    public EmployeeAttdReportFileWriter(JdbcTemplate jdbcTemplate, AppConfigurationProperties configuration) {
        this.jdbcTemplate = jdbcTemplate;
        this.configuration = configuration;
    }

    Logger logger = Logger.getLogger(getClass().getName());

    private static String[] names = new String[]{
            "employee_id",
            "first_name",
            "last_name",
            "email",
            "category",
            "division",
            "total_attend_days",
            "total_late_days",
            "total_overtime_hours",
            "total_paid_leave_days",
            "paid_leave_limit_remaining"
    };

    @Override
    public void write(Chunk<? extends EmployeeAttendanceDataDto> chunk) throws Exception {
        /*
            Step:
            1. calculate
            2. write report to csv (format: EmployeeAttendanceReportDto)
            3. update past and current leave in employee table
         */

        List<EmployeeAttendanceReportDto> reportList = new ArrayList<>();

        chunk.forEach(item -> {
            Employee employee = item.getEmployee();
            List<Attendance> attendanceList = item.getAttendanceList();

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

            reportList.add(new EmployeeAttendanceReportDto(
                    employee.getEmployeeId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getCategory(),
                    employee.getCategory(),
                    employee.getDivision(),
                    totalAttendDays,
                    totalLateDays,
                    totalOvertimeHours.get(),
                    totalPaidLeaveDays,
                    paidLeaveLimitRemaining
            ));
        });

        batchUpdatePaidLeaveData(reportList);
        writeReportCsv(reportList);
    }

    private void writeReportCsv(List<EmployeeAttendanceReportDto> data) throws IOException {
        File file = new FileSystemResource("employee_attendance_monthly_report.csv").getFile();
        boolean isNewFile = file.createNewFile();
        try {
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);

            if (isNewFile) {
                // header
                String[] header = names;
                writer.writeNext(header);
            }

            // data
            List<String[]> stringData = new ArrayList<>();
            data.forEach(x -> stringData.add(new String[]{x.getEmployeeId(), x.getFirstName(), x.getLastName(), x.getEmail(), x.getCategory(), x.getDivision(), x.getTotal_attend_days().toString(), x.getTotal_late_days().toString(), x.getTotal_overtime_hours().toString(), x.getTotal_paid_leave_days().toString(), x.getPaid_leave_limit_remaining().toString()}));
            writer.writeAll(stringData);

            writer.close();

            logger.info("Successfully writing report csv");
        } catch (IOException e) {
            logger.info("Failed writing report csv");
            throw new IOException(e);
        }
    }

    public void batchUpdatePaidLeaveData(List<EmployeeAttendanceReportDto> dataList) throws Exception {
        try {
            jdbcTemplate.batchUpdate(
                    "update EMPLOYEE_DATA set used_paid_leave = ? where employee_id = ?",
                    new BatchPreparedStatementSetter() {
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            EmployeeAttendanceReportDto data = dataList.get(i);
                            ps.setInt(1, data.getPaid_leave_limit_remaining());
                            ps.setString(2, data.getEmployeeId());
                        }

                        public int getBatchSize() {
                            return dataList.size();
                        }
                    });


            logger.info("Successfully updating data to database");
        } catch (Exception e) {
            logger.info("Failed updating data to database");
            throw new Exception(e);
        }

    }
}
