package one.bca.employee_attendance_report_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableBatchProcessing
@EnableScheduling
public class EmployeeAttendanceReportBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeAttendanceReportBatchApplication.class, args);
    }

}
