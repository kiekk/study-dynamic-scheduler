package com.example.studydynamicschedulerv2.batch.runner;

import com.example.studydynamicschedulerv2.entity.scheduler.ProductScheduler;
import com.example.studydynamicschedulerv2.exception.ApiException;
import com.example.studydynamicschedulerv2.repository.product.ProductSchedulerRepository;
import com.example.studydynamicschedulerv2.service.job.JobService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductJobRunner extends JobRunner {
    private final ProductSchedulerRepository productSchedulerRepository;
    private final JobService jobService;

    public ProductJobRunner(ProductSchedulerRepository productSchedulerRepository, JobService jobService) {
        this.productSchedulerRepository = productSchedulerRepository;
        this.jobService = jobService;
    }

    @Override
    protected void doRun(ApplicationArguments args) {
        List<ProductScheduler> list = productSchedulerRepository.findAll();

        list.forEach(productScheduler -> {
            try {
                jobService.register(productScheduler);
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
