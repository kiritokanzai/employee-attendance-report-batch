package one.bca.employee_attendance_report_batch.writer;

import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceReportDto;
import org.apache.coyote.BadRequestException;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EmployeeAttdReportFileWriter implements ItemWriter<EmployeeAttendanceReportDto> {
    private final JdbcTemplate jdbcTemplate;

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
    public void write(Chunk<? extends EmployeeAttendanceReportDto> chunk) throws Exception {
        batchUpdatePaidLeaveData(chunk);
        writeReportCsv(chunk);
    }

    private synchronized void writeReportCsv(Chunk<? extends EmployeeAttendanceReportDto> data) throws IOException {
        File file = new FileSystemResource("report/employee_attendance_monthly_report.csv").getFile();
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

            log.info("Successfully writing report csv");
        } catch (IOException e) {
            log.info("Failed writing report csv");
            throw new IOException(e);
        }
    }

    public void batchUpdatePaidLeaveData(Chunk<? extends EmployeeAttendanceReportDto> dataList) throws Exception {
        try {
            dataList.forEach((report) -> {
                jdbcTemplate.update(
                        "update EMPLOYEE_DATA set used_paid_leave = ? where employee_id = ?",
                        ps -> {
                            ps.setInt(1, report.getPaid_leave_limit_remaining());
                            ps.setString(2, report.getEmployeeId());
                        }
                );
            });

            log.info("Successfully updating data to database");
        } catch (Exception e) {
            log.info("Failed updating data to database");
            throw new BadRequestException(e);
        }
    }
}
