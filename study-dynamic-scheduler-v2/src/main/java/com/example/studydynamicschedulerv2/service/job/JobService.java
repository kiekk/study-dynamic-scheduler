package com.example.studydynamicschedulerv2.service.job;

import com.example.studydynamicschedulerv2.batch.job.ProductScheduleJob;
import com.example.studydynamicschedulerv2.entity.scheduler.ProductScheduler;
import com.example.studydynamicschedulerv2.enums.common.ApiExceptionType;
import com.example.studydynamicschedulerv2.enums.scheduler.ExecuteType;
import com.example.studydynamicschedulerv2.exception.ApiException;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final Scheduler scheduler;

    public JobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void register(ProductScheduler productScheduler) throws ApiException {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("productSchedulerId", productScheduler.getId());
            jobDataMap.put("productSchedulerName", productScheduler.getName());
            jobDataMap.put("productId", productScheduler.getProduct().getId());
            jobDataMap.put("productTitle", productScheduler.getProduct().getTitle());
            JobDetail jobDetail = JobBuilder.newJob(ProductScheduleJob.class).withIdentity(productScheduler.getId(), "productSchedulerJob").usingJobData(jobDataMap).build();

            Trigger trigger = null;

            if (productScheduler.getExecuteType() == ExecuteType.CRON) {
                trigger = TriggerBuilder.newTrigger().withIdentity(productScheduler.getId(), "productSchedulerJob").withSchedule(CronScheduleBuilder.cronSchedule(productScheduler.getExpression())).build();
            }

            scheduler.scheduleJob(jobDetail, trigger);

            if (!productScheduler.isEnabled()) {
                pause(productScheduler.getId());
            }
        } catch (Exception e) {
            throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "등록");
        }
    }

    public void update(ProductScheduler productScheduler) throws ApiException {
        try {
            Trigger trigger = null;

            if (productScheduler.getExecuteType() == ExecuteType.CRON) {
                trigger = TriggerBuilder.newTrigger().withIdentity(productScheduler.getId(), "productSchedulerJob").withSchedule(CronScheduleBuilder.cronSchedule(productScheduler.getExpression())).build();
            }

            scheduler.rescheduleJob(new TriggerKey(productScheduler.getId(), "productSchedulerJob"), trigger);

            if (!productScheduler.isEnabled()) {
                pause(productScheduler.getId());
            }
        } catch (Exception e) {
            throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "수정");
        }
    }

    public void delete(String productSchedulerId) throws ApiException {
        try {
            if (isRegisteredSchedule(productSchedulerId)) {
                scheduler.unscheduleJob(new TriggerKey(productSchedulerId, "productSchedulerJob"));
            }
        } catch (Exception e) {
            throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "삭제");
        }
    }

    public void resume(ProductScheduler productScheduler) throws ApiException {
        try {
            if (isRegisteredSchedule(productScheduler.getId())) {
                scheduler.resumeJob(new JobKey(productScheduler.getId(), "productSchedulerJob"));
            } else {
                register(productScheduler);
            }
        } catch (Exception e) {
            throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "활성화");
        }
    }

    public void pause(String productSchedulerId) throws ApiException {
        try {
            scheduler.pauseJob(new JobKey(productSchedulerId, "productSchedulerJob"));
        } catch (Exception e) {
            throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "비활성화");
        }
    }

    private boolean isRegisteredSchedule(String productSchedulerId) throws SchedulerException {
        return scheduler.getJobDetail(new JobKey(productSchedulerId, "productSchedulerIdJob")) != null;
    }

}
