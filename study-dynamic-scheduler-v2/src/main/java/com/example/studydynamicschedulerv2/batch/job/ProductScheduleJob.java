package com.example.studydynamicschedulerv2.batch.job;

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

public class ProductScheduleJob extends QuartzJobBean {

    private final Job connectionJob;

    private final JobLauncher jobLauncher;

    public ProductScheduleJob(Job connectionJob, JobLauncher jobLauncher) {
        this.connectionJob = connectionJob;
        this.jobLauncher = jobLauncher;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        String productSchedulerId = (String) context.getJobDetail().getJobDataMap().get("productSchedulerId");
        String productSchedulerName = (String) context.getJobDetail().getJobDataMap().get("productSchedulerName");
        String productId = (String) context.getJobDetail().getJobDataMap().get("productId");
        String productTitle = (String) context.getJobDetail().getJobDataMap().get("productTitle");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("id", new Date().getTime())
                .addString("productSchedulerId", productSchedulerId)
                .addString("productSchedulerName", productSchedulerName)
                .addString("productId", productId)
                .addString("productTitle", productTitle)
                .toJobParameters();
        try {
            jobLauncher.run(connectionJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

}
