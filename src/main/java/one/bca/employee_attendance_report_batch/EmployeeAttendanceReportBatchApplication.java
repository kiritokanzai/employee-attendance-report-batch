package one.bca.employee_attendance_report_batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class EmployeeAttendanceReportBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAttendanceReportBatchApplication.class, args);
	}

}
