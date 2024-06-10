package one.bca.employee_attendance_report_batch.configuration;

import one.bca.employee_attendance_report_batch.model.Attendace;
import one.bca.employee_attendance_report_batch.model.Employee;
import one.bca.employee_attendance_report_batch.reader.AttendanceReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AttendanceConfiguration {
    private final JobRepository jobRepository;
    private final DataSourceTransactionManager transactionManager;
    private final AttendanceReader reader;

    public AttendanceConfiguration(JobRepository jobRepository, DataSourceTransactionManager transactionManager, AttendanceReader reader) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.reader = reader;
    }

    public Step attendanceDataStep() {
        return new StepBuilder("attendanceDataStep", jobRepository)
                .<Attendace, Attendace>chunk(50, transactionManager)
                .reader(reader.itemReader())
                .processor(new ItemProcessor<Attendace, Attendace>() {
                    @Override
                    public Attendace process(Attendace item) throws Exception {
                        return item;
                    }
                })
                .build();
    }
}
