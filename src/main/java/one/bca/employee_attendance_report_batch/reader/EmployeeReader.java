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

    private final EmployeeRowMapper employeeRowMapper;

    public EmployeeReader(DataSourceTransactionManager transactionManager, EmployeeRowMapper employeeRowMapper) {
        this.transactionManager = transactionManager;
        this.employeeRowMapper = employeeRowMapper;
    }

    public static String GET_SQL = "select "
            + "employee_id, first_name, last_name, gender, email, category, paid_leave_limit, used_paid_leave "
            + "from EMPLOYEES order by employee_id";

    public ItemReader<Employee> itemReader() {
        return new JdbcCursorItemReaderBuilder<Employee>()
                .dataSource(transactionManager.getDataSource())
                .name("jdbcCursorEmployeeReader")
                .sql(GET_SQL)
                .rowMapper(employeeRowMapper)
                .build();
    }
}
