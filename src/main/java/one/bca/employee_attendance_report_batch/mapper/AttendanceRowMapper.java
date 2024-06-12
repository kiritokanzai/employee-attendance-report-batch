package one.bca.employee_attendance_report_batch.mapper;

import one.bca.employee_attendance_report_batch.enumHelper.AttendanceStatusEnum;
import one.bca.employee_attendance_report_batch.model.Attendance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceRowMapper implements RowMapper<Attendance> {
    @Override
    public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(rs.getString("employee_id"));
        attendance.setDate(rs.getDate("date"));
        attendance.setClockIn(rs.getTime("clock_in"));
        attendance.setClockOut(rs.getTime("clock_out"));
        attendance.setAttendanceStatus(Enum.valueOf(AttendanceStatusEnum.class, rs.getString("attendance_status")));
        attendance.setOvertimeStatus(rs.getBoolean("overtime_status"));
        attendance.setOvertimeStart(rs.getTime("overtime_start"));
        attendance.setOvertimeEnd(rs.getTime("overtime_end"));

        return attendance;
    }
}
