package one.bca.employee_attendance_report_batch.reader;

import one.bca.employee_attendance_report_batch.mapper.EmployeeRowMapper;
import one.bca.employee_attendance_report_batch.model.Employee;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class EmployeeReader {
    private final DataSourceTransactionManager transactionManager;

    public EmployeeReader(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    private static final String GET_SQL = "select " +
            "e.employee_id, e.first_name, e.last_name, e.gender, e.email, e.category, e.paid_leave_limit, e.last_used_paid_leave, e.current_used_paid_leave " +
            "from EMPLOYEE_DATA e " +
            "order by e.employee_id";

    public ItemReader<Employee> itemReader() {
        return new JdbcCursorItemReaderBuilder<Employee>()
                .dataSource(transactionManager.getDataSource())
                .name("jdbcCursorEmployeeReader")
                .sql(GET_SQL)
                .rowMapper(new EmployeeRowMapper())
                .build();
    }
}
