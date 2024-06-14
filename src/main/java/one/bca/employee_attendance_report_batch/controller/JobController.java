package one.bca.employee_attendance_report_batch.controller;

import lombok.RequiredArgsConstructor;
import one.bca.employee_attendance_report_batch.configuration.JobConfiguration;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {
    private final JobLauncher jobLauncher;
    private final JobConfiguration jobConfiguration;

    @GetMapping("/start-job")
    public String startJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(jobConfiguration.job(), jobParameters);
            return "Job started successfully";
        } catch (Exception e) {
            return "Job failed to start: " + e.getMessage();
        }
    }
}
