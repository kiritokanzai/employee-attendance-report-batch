package one.bca.employee_attendance_report_batch.configuration;

import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceDataDto;
import one.bca.employee_attendance_report_batch.model.Employee;
import one.bca.employee_attendance_report_batch.processor.EmployeeAttendanceProcessor;
import one.bca.employee_attendance_report_batch.reader.EmployeeReader;
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
    private final EmployeeReader reader;
    private final EmployeeAttendanceProcessor processor;

    public EmployeeAttdReportStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, EmployeeReader reader, EmployeeAttendanceProcessor processor) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.reader = reader;
        this.processor = processor;
    }

    public Step getStep() {
        return new StepBuilder("employeeDataStep", jobRepository)
                .<Employee, EmployeeAttendanceDataDto>chunk(50, transactionManager)
                .reader(reader.itemReader())
                .processor(processor)
                .writer(new EmployeeAttdReportFileWriter())
                .build();
    }
}
