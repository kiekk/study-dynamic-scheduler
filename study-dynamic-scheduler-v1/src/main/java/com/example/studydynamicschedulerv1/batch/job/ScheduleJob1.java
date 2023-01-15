package com.example.studydynamicschedulerv1.batch.job;

import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class ScheduleJob1 extends QuartzJobBean {

    private final Job batchJob1;
    private final JobLauncher jobLauncher;

    public ScheduleJob1(Job batchJob1, JobLauncher jobLauncher) {
        this.batchJob1 = batchJob1;
        this.jobLauncher = jobLauncher;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("id", new Date().getTime())
                .toJobParameters();
        try {
            jobLauncher.run(batchJob1, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

}
