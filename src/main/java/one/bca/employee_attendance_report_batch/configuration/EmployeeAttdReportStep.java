package one.bca.employee_attendance_report_batch.configuration;

import lombok.RequiredArgsConstructor;
import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceReportDto;
import one.bca.employee_attendance_report_batch.model.Employee;
import one.bca.employee_attendance_report_batch.processor.EmployeeAttendanceProcessor;
import one.bca.employee_attendance_report_batch.writer.EmployeeAttdReportFileWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@RequiredArgsConstructor
public class EmployeeAttdReportStep {
    private final JobRepository jobRepository;
    private final DataSourceTransactionManager transactionManager;
    private final EmployeeAttendanceProcessor processor;
    private final EmployeeAttdReportFileWriter writer;
    private final ItemReader<Employee> reader;

    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("employee-attd-report");
    }

    public Step getStep() {
        return new StepBuilder("employeeDataStep", jobRepository)
                .<Employee, EmployeeAttendanceReportDto>chunk(50, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .taskExecutor(taskExecutor())
                .build();
    }
}
