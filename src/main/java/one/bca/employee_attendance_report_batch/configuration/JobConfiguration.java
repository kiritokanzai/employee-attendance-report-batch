package one.bca.employee_attendance_report_batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class JobConfiguration {
    private final JobRepository jobRepository;
    private final AttendanceDataStep attendanceDataStep;
    private final EmployeeDataStep employeeDataStep;

    public JobConfiguration(JobRepository jobRepository, AttendanceDataStep attendanceDataStep, EmployeeDataStep employeeDataStep) {
        this.jobRepository = jobRepository;
        this.attendanceDataStep = attendanceDataStep;
        this.employeeDataStep = employeeDataStep;
    }

    public Job job() throws Exception {
        return new JobBuilder("employeeAttendanceReportJob", jobRepository)
                .start(attendanceDataStep.getStep())
                .next(employeeDataStep.getStep())
                .build();
    }
}
