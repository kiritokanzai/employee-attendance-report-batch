package one.bca.employee_attendance_report_batch.mapper;

import one.bca.employee_attendance_report_batch.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getString("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setGender(rs.getString("gender"));
        employee.setEmail(rs.getString("email"));
        employee.setCategory(rs.getString("category"));
        employee.setPaidLeaveLimit(rs.getInt("paid_leave_limit"));
        employee.setLastUsedPaidLeave(rs.getInt("last_used_paid_leave"));
        employee.setCurrentUsedPaidLeave(rs.getInt("current_used_paid_leave"));

        return employee;
    }
}
