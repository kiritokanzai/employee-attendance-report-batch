package one.bca.employee_attendance_report_batch.configuration;

import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceDataDto;
import one.bca.employee_attendance_report_batch.model.EmployeeAttendance;
import one.bca.employee_attendance_report_batch.reader.EmployeeAttendanceReader;
import one.bca.employee_attendance_report_batch.writer.EmployeeAttdReportFileWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class EmployeeAttdReportStep {
    private final JobRepository jobRepository;
    private final DataSourceTransactionManager transactionManager;
    private final EmployeeAttendanceReader reader;

    public EmployeeAttdReportStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, EmployeeAttendanceReader reader) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.reader = reader;
    }

    public Step getStep() {
        return new StepBuilder("employeeDataStep", jobRepository)
                .<EmployeeAttendance, EmployeeAttendance>chunk(50, transactionManager)
                .reader(reader.itemReader())
                .writer(new EmployeeAttdReportFileWriter())
                .build();
    }
}
