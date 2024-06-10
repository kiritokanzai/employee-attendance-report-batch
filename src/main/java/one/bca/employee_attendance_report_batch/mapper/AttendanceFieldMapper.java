package one.bca.employee_attendance_report_batch.mapper;

import one.bca.employee_attendance_report_batch.model.Attendance;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.sql.Time;

public class AttendanceFieldMapper implements FieldSetMapper<Attendance> {
    @Override
    public Attendance mapFieldSet(FieldSet fieldSet) throws BindException {
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(fieldSet.readString("employee_id"));
        attendance.setDate(fieldSet.readDate("date"));
        attendance.setClockIn(Time.valueOf(fieldSet.readString("clock_in")));
        attendance.setClockOut(Time.valueOf(fieldSet.readString("clock_out")));
        attendance.setOvertimeStatus(fieldSet.readBoolean("overtime_status"));
        attendance.setOvertimeStart(Time.valueOf(fieldSet.readString("overtime_start")));
        attendance.setOvertimeEnd(Time.valueOf(fieldSet.readString("overtime_end")));
        return attendance;
    }
}
