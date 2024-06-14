package one.bca.employee_attendance_report_batch.reader;

import one.bca.employee_attendance_report_batch.mapper.EmployeeRowMapper;
import one.bca.employee_attendance_report_batch.model.Employee;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class EmployeeReader {
    private final DataSourceTransactionManager transactionManager;

    public EmployeeReader(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public ItemReader<Employee> itemReader() {
        JdbcPagingItemReader<Employee> reader = new JdbcPagingItemReader<>();
        final SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean = getSqlPagingQueryProviderFactoryBean();

        try {
            reader.setQueryProvider(sqlPagingQueryProviderFactoryBean.getObject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        reader.setDataSource(transactionManager.getDataSource());
        reader.setPageSize(50);
        reader.setRowMapper(new EmployeeRowMapper());
        return reader;
    }

    private SqlPagingQueryProviderFactoryBean getSqlPagingQueryProviderFactoryBean() {
        final SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
        sqlPagingQueryProviderFactoryBean.setDataSource(transactionManager.getDataSource());
        sqlPagingQueryProviderFactoryBean.setSelectClause("select employee_id, first_name, last_name, gender, email, category, division, paid_leave_limit, used_paid_leave");
        sqlPagingQueryProviderFactoryBean.setFromClause("from employee_data");
        sqlPagingQueryProviderFactoryBean.setSortKey("employee_id");
        return sqlPagingQueryProviderFactoryBean;
    }
}
