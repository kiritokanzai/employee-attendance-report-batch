package one.bca.employee_attendance_report_batch.configuration_properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "app")
public class AppConfigurationProperties {
    String reportFilePath;
    RuleConfigurationProperties rule;
}
