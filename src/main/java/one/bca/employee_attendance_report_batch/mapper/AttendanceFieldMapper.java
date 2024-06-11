package one.bca.employee_attendance_report_batch.mapper;

import one.bca.employee_attendance_report_batch.enumHelper.AttendanceStatusEnum;
import one.bca.employee_attendance_report_batch.model.Attendance;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class AttendanceFieldMapper implements FieldSetMapper<Attendance> {
    @Override
    public Attendance mapFieldSet(FieldSet fieldSet) throws BindException {

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(fieldSet.readString("employee_id"));
        attendance.setDate(fieldSet.readDate("date", "dd/MM/yyyy"));
        if(!fieldSet.readString("clock_in").isEmpty()) {
            attendance.setClockIn(Time.valueOf(LocalTime.parse(fieldSet.readString("clock_in"))));
        }
        if(!fieldSet.readString("clock_out").isEmpty()) {
            attendance.setClockOut(Time.valueOf(LocalTime.parse(fieldSet.readString("clock_out"))));
        }
        attendance.setAttendanceStatus(Enum.valueOf(AttendanceStatusEnum.class, fieldSet.readString("attendance_status").toUpperCase()));
        attendance.setOvertimeStatus(fieldSet.readBoolean("overtime_status"));
        if(!fieldSet.readString("overtime_start").isEmpty()) {
            attendance.setOvertimeStart(Time.valueOf(LocalTime.parse(fieldSet.readString("overtime_start"))));
        }
        if(!fieldSet.readString("overtime_end").isEmpty()) {
            attendance.setOvertimeEnd(Time.valueOf(LocalTime.parse(fieldSet.readString("overtime_end"))));
        }
        return attendance;
    }
}
