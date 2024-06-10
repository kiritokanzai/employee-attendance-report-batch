package one.bca.employee_attendance_report_batch.model;

import java.sql.Time;
import java.util.Date;

public class Attendance {
    private String employeeId;
    private Date date;
    private Time clockIn;
    private Time clockOut;
    private boolean attendanceStatus;
    private boolean overtimeStatus;
    private Time overtimeStart;
    private Time overtimeEnd;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getClockIn() {
        return clockIn;
    }

    public void setClockIn(Time clockIn) {
        this.clockIn = clockIn;
    }

    public Time getClockOut() {
        return clockOut;
    }

    public void setClockOut(Time clockOut) {
        this.clockOut = clockOut;
    }

    public boolean isAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public boolean isOvertimeStatus() {
        return overtimeStatus;
    }

    public void setOvertimeStatus(boolean overtimeStatus) {
        this.overtimeStatus = overtimeStatus;
    }

    public Time getOvertimeStart() {
        return overtimeStart;
    }

    public void setOvertimeStart(Time overtimeStart) {
        this.overtimeStart = overtimeStart;
    }

    public Time getOvertimeEnd() {
        return overtimeEnd;
    }

    public void setOvertimeEnd(Time overtimeEnd) {
        this.overtimeEnd = overtimeEnd;
    }

    @Override
    public String toString() {
        return "Attendace[" +
                "employeeId=" + employeeId +
                ", date=" + date +
                ", clockIn=" + clockIn +
                ", clockOut=" + clockOut +
                ", attendanceStatus=" + attendanceStatus +
                ", overtimeStatus=" + overtimeStatus +
                ", overtimeStart=" + overtimeStart +
                ", overtimeEnd=" + overtimeEnd +
                ']';
    }
}
