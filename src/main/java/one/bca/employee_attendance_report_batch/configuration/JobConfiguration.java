package one.bca.employee_attendance_report_batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {
    private final JobRepository jobRepository;
    private final AttendanceDataStep attendanceDataStep;
    private final EmployeeAttdReportStep employeeAttdReportStep;

    public JobConfiguration(JobRepository jobRepository, AttendanceDataStep attendanceDataStep, EmployeeAttdReportStep employeeAttdReportStep) {
        this.jobRepository = jobRepository;
        this.attendanceDataStep = attendanceDataStep;
        this.employeeAttdReportStep = employeeAttdReportStep;
    }

    public Job job() throws Exception {
        return new JobBuilder("employeeAttendanceReportJob", jobRepository)
                .start(attendanceDataStep.getStep())
                .next(employeeAttdReportStep.getStep())
                .build();
    }
}
