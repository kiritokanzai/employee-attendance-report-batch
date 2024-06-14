package one.bca.employee_attendance_report_batch.writer;

import lombok.RequiredArgsConstructor;
import one.bca.employee_attendance_report_batch.mapper.AttendanceItemPreparedStatementSetter;
import one.bca.employee_attendance_report_batch.model.Attendance;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class AttendanceDataJdbcWriter {
    private static final String INSERT_ATTENDANCE_SQL = "insert into " +
            "EMPLOYEE_ATTENDANCE(employee_id, date, clock_in, clock_out, attendance_status, overtime_status, overtime_start, overtime_end) " +
            "values(?,?,?,?,?,?,?,?)";

    public ItemWriter<Attendance> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Attendance>()
                .dataSource(dataSource)
                .sql(INSERT_ATTENDANCE_SQL)
                .itemPreparedStatementSetter(new AttendanceItemPreparedStatementSetter())
                .build();
    }
}
