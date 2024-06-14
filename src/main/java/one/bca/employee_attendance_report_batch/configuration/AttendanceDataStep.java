package one.bca.employee_attendance_report_batch.configuration;

import lombok.RequiredArgsConstructor;
import one.bca.employee_attendance_report_batch.model.Attendance;
import one.bca.employee_attendance_report_batch.reader.AttendanceReader;
import one.bca.employee_attendance_report_batch.writer.AttendanceDataJdbcWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@RequiredArgsConstructor
public class AttendanceDataStep {
    private final JobRepository jobRepository;
    private final DataSourceTransactionManager transactionManager;
    private final AttendanceReader reader;

    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("attendance-data-task");
    }

    public Step getStep() {
        return new StepBuilder("attendanceDataStep", jobRepository)
                .<Attendance, Attendance>chunk(50, transactionManager)
                .reader(reader.itemReader())
                .writer(new AttendanceDataJdbcWriter().itemWriter(transactionManager.getDataSource()))
                .taskExecutor(taskExecutor())
                .build();
    }
}
