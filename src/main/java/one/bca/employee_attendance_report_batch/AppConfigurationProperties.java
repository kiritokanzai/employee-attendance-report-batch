package one.bca.employee_attendance_report_batch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;

@Configuration
@Data
@ConfigurationProperties(prefix = "app")
public class AppConfigurationProperties {
    Time attendanceClockIn;
    Time attendanceClockOut;
    Time startOvertimeHour;
}
