package one.bca.employee_attendance_report_batch.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {
    private final JobRepository jobRepository;
    private final AttendanceDataStep attendanceDataStep;
    private final EmployeeAttdReportStep employeeAttdReportStep;

    @Bean
    public Job job() {
        return new JobBuilder("employeeAttendanceReportJob", jobRepository)
                .start(attendanceDataStep.getStep())
                .next(employeeAttdReportStep.getStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
