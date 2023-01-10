package com.example.studydynamicschedulerv2.batch.job;

import com.example.studydynamicschedulerv2.batch.listener.CustomJobAnnotationExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProductJob {
    private final CustomJobAnnotationExecutionListener listener;

    public ProductJob(CustomJobAnnotationExecutionListener listener) {
        this.listener = listener;
    }


    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("productJob", jobRepository)
                .start(step)
                .listener(listener)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    String productSchedulerId = (String) contribution.getStepExecution().getJobExecution().getJobParameters().getParameters().get("productSchedulerId").getValue();
                    String productSchedulerName = (String) contribution.getStepExecution().getJobExecution().getJobParameters().getParameters().get("productSchedulerName").getValue();
                    String productId = (String) contribution.getStepExecution().getJobExecution().getJobParameters().getParameters().get("productId").getValue();
                    String productTitle = (String) contribution.getStepExecution().getJobExecution().getJobParameters().getParameters().get("productTitle").getValue();

                    System.out.println("============");
                    System.out.println("Batch Start");
                    System.out.println("productScheduler Id : " + productSchedulerId);
                    System.out.println("productScheduler  Name : " + productSchedulerName);
                    System.out.println("Product Id : " + productId);
                    System.out.println("Product Title : " + productTitle);
                    System.out.println("============");

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
