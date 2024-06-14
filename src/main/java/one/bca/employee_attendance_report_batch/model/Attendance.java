package one.bca.employee_attendance_report_batch.model;

import lombok.Data;
import one.bca.employee_attendance_report_batch.enum_helper.AttendanceStatusEnum;

import java.sql.Time;
import java.util.Date;

@Data
public class Attendance {
    private String employeeId;
    private Date date;
    private Time clockIn;
    private Time clockOut;
    private AttendanceStatusEnum attendanceStatus;
    private boolean overtimeStatus;
    private Time overtimeStart;
    private Time overtimeEnd;
}
