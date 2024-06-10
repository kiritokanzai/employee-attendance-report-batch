package one.bca.employee_attendance_report_batch.writer;

import one.bca.employee_attendance_report_batch.mapper.AttendanceItemPreparedStatementSetter;
import one.bca.employee_attendance_report_batch.model.Attendance;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;

import javax.sql.DataSource;

public class AttendanceDataWriter {
    public static String INSERT_ORDER_SQL = "insert into " +
            "EMPLOYEE_ATTENDANCE(employee_id, date, clock_in, clock_out, attendance_status, overtime_status, overtime_start, overtime_end) " +
            "values(?,?,?,?,?,?,?,?)";

    public ItemWriter<Attendance> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Attendance>()
                .dataSource(dataSource)
                .sql(INSERT_ORDER_SQL)
                .itemPreparedStatementSetter(new AttendanceItemPreparedStatementSetter())
                .build();
    }
}
