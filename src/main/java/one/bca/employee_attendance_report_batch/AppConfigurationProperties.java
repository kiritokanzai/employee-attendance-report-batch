package one.bca.employee_attendance_report_batch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfigurationProperties {
    Time attendanceClockIn;
    Time attendanceClockOut;
    Time startOvertimeHour;

    public Time getAttendanceClockIn() {
        return attendanceClockIn;
    }

    public void setAttendanceClockIn(Time attendanceClockIn) {
        this.attendanceClockIn = attendanceClockIn;
    }

    public Time getAttendanceClockOut() {
        return attendanceClockOut;
    }

    public void setAttendanceClockOut(Time attendanceClockOut) {
        this.attendanceClockOut = attendanceClockOut;
    }

    public Time getStartOvertimeHour() {
        return startOvertimeHour;
    }

    public void setStartOvertimeHour(Time startOvertimeHour) {
        this.startOvertimeHour = startOvertimeHour;
    }
}
