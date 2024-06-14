package one.bca.employee_attendance_report_batch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobScheduler {
    private final Job job;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0 0 0 1 * *") // Schedule at first day of the month
    public void performBatchJob() throws Exception {
        log.info("Running job {} on {}", job.getName(), LocalDateTime.now());
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
    }

}
