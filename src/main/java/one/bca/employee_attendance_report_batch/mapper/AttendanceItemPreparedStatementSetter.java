package one.bca.employee_attendance_report_batch.mapper;

import one.bca.employee_attendance_report_batch.model.Attendance;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttendanceItemPreparedStatementSetter implements ItemPreparedStatementSetter<Attendance> {
    @Override
    public void setValues(Attendance item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getEmployeeId());
        ps.setDate(2, new Date(item.getDate().getTime()));
        ps.setTime(3, item.getClockIn());
        ps.setTime(4, item.getClockOut());
        ps.setBoolean(5, item.isAttendanceStatus());
        ps.setBoolean(6, item.isOvertimeStatus());
        ps.setTime(7, item.getOvertimeStart());
        ps.setTime(8, item.getOvertimeEnd());
    }
}
