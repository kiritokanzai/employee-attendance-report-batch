package one.bca.employee_attendance_report_batch.configuration_properties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;

@Configuration
@Data
public class RuleConfigurationProperties {
    Time attendanceClockIn;
    Time attendanceClockOut;
    Time startOvertimeHour;
}
