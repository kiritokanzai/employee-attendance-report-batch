package one.bca.employee_attendance_report_batch.reader;

import one.bca.employee_attendance_report_batch.mapper.EmployeeAttendanceRowMapper;
import one.bca.employee_attendance_report_batch.model.EmployeeAttendance;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAttendanceReader {
    private final DataSourceTransactionManager transactionManager;

    public EmployeeAttendanceReader(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    private static final String GET_SQL = "select " +
            "e.employee_id, e.first_name, e.last_name, e.gender, e.email, e.category, e.paid_leave_limit, e.used_paid_leave, " +
            "ea.date, ea.clock_in, ea.clock_out, ea.attendance_status, ea.overtime_status, ea.overtime_start, ea.overtime_end " +
            "from EMPLOYEES e join EMPLOYEE_ATTENDANCE ea ON e.employee_id = ea.employee_id " +
            "order by e.employee_id";

    public ItemReader<EmployeeAttendance> itemReader() {
        return new JdbcCursorItemReaderBuilder<EmployeeAttendance>()
                .dataSource(transactionManager.getDataSource())
                .name("jdbcCursorEmployeeReader")
                .sql(GET_SQL)
                .rowMapper(new EmployeeAttendanceRowMapper())
                .build();
    }
}
