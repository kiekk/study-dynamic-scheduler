package com.example.studydynamicschedulerv2.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Component
public class CustomJobAnnotationExecutionListener {

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        String productSchedulerId = jobExecution.getJobParameters().getString("productSchedulerId");
        String productSchedulerName = jobExecution.getJobParameters().getString("productSchedulerName");
        String productId = jobExecution.getJobParameters().getString("productId");
        String productTitle = jobExecution.getJobParameters().getString("productTitle");

        System.out.println("============");
        System.out.println("Batch Before");
        System.out.println("productScheduler Id : " + productSchedulerId);
        System.out.println("productScheduler  Name : " + productSchedulerName);
        System.out.println("Product Id : " + productId);
        System.out.println("Product Title : " + productTitle);
        System.out.println("============");
    }

}
