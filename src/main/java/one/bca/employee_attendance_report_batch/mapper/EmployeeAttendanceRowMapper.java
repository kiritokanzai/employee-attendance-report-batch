package one.bca.employee_attendance_report_batch.mapper;

import one.bca.employee_attendance_report_batch.enumHelper.AttendanceStatusEnum;
import one.bca.employee_attendance_report_batch.model.Attendance;
import one.bca.employee_attendance_report_batch.model.Employee;
import one.bca.employee_attendance_report_batch.model.EmployeeAttendance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeAttendanceRowMapper implements RowMapper<EmployeeAttendance> {
    @Override
    public EmployeeAttendance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getString("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setGender(rs.getString("gender"));
        employee.setEmail(rs.getString("email"));
        employee.setCategory(rs.getString("category"));
        employee.setPaidLeaveLimit(rs.getInt("paid_leave_limit"));
        employee.setUsedPaidLeave(rs.getInt("used_paid_leave"));

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(rs.getString("employee_id"));
        attendance.setDate(rs.getDate("date"));
        attendance.setClockIn(rs.getTime("clock_in"));
        attendance.setClockOut(rs.getTime("clock_out"));
        attendance.setAttendanceStatus(Enum.valueOf(AttendanceStatusEnum.class, rs.getString("attendance_status")));
        attendance.setOvertimeStatus(rs.getBoolean("pvertime_status"));
        attendance.setOvertimeStart(rs.getTime("overtime_start"));
        attendance.setOvertimeEnd(rs.getTime("overtime_end"));

        return new EmployeeAttendance(employee, attendance);
    }
}
