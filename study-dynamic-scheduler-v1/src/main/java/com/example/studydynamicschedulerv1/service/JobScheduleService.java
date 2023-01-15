package com.example.studydynamicschedulerv1.service;

import com.example.studydynamicschedulerv1.batch.job.ScheduleJob1;
import com.example.studydynamicschedulerv1.batch.job.ScheduleJob2;
import com.example.studydynamicschedulerv1.entity.JobSchedule;
import com.example.studydynamicschedulerv1.enums.common.ApiExceptionType;
import com.example.studydynamicschedulerv1.exception.ApiException;
import com.example.studydynamicschedulerv1.repository.JobScheduleRepository;
import org.quartz.*;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobScheduleService {

    private final JobScheduleRepository repository;
    private final Scheduler scheduler;


    public JobScheduleService(JobScheduleRepository repository, Scheduler scheduler) {
        this.repository = repository;
        this.scheduler = scheduler;
    }

    public List<JobSchedule> search() {
        return repository.findAll();
    }

    public void delete(JobSchedule jobSchedule) throws ApiException {
        try {
            scheduler.unscheduleJob(new TriggerKey(jobSchedule.getId()));
        } catch (Exception e) {
            throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "삭제");
        }
    }

    public List<JobSchedule> registeredList() throws ApiException {
        List<JobSchedule> result = new ArrayList<>();
        try {
            for (JobKey jobKey : scheduler.getJobKeys(null)) {
                JobSchedule jobSchedule = repository.findByJobName(jobKey.getName()).orElseThrow(() -> new ApiException(ApiExceptionType.FAILED_TO_FETCH, "등록된 잡"));
                result.add(jobSchedule);
            }
        } catch (SchedulerException e) {
            throw new ApiException(ApiExceptionType.FAILED_TO_FETCH, "등록된 잡");
        }
        return result;
    }

    public void register(String id) throws ApiException, SchedulerException {

        boolean overlap = false;
        JobSchedule jobSchedule = repository.findById(id).orElseThrow(() -> new ApiException(ApiExceptionType.FAILED_TO_FETCH, "잡"));
        for (JobKey jobKey : scheduler.getJobKeys(null)) {
            if (StringUtils.equalsIgnoreCase(jobKey.getName(), jobSchedule.getJobName())) {
                overlap = true;
            }
        }

        JobDataMap jobDataMap = new JobDataMap();
        JobDetail jobDetail;

        if ("batchJob1".equals(jobSchedule.getJobName())) {
            jobDetail = JobBuilder.newJob(ScheduleJob1.class).withIdentity(jobSchedule.getJobName()).usingJobData(jobDataMap).build();
        } else {
            jobDetail = JobBuilder.newJob(ScheduleJob2.class).withIdentity(jobSchedule.getJobName()).usingJobData(jobDataMap).build();
        }

        if (overlap) {
            try {
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobSchedule.getId()).withSchedule(CronScheduleBuilder.cronSchedule(jobSchedule.getTriggerCron())).forJob(jobSchedule.getJobName()).build();
                scheduler.scheduleJob(trigger);
            } catch (SchedulerException e) {
                throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "등록");
            }
        } else {
            try {
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobSchedule.getId()).withSchedule(CronScheduleBuilder.cronSchedule(jobSchedule.getTriggerCron())).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } catch (SchedulerException e) {
                throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "수정");
            }
        }

    }

    public void update(String id, JobSchedule schedule) throws ApiException {
        JobSchedule jobSchedule = repository.findById(id).orElseThrow(() -> new ApiException(ApiExceptionType.FAILED_TO_FETCH, "잡"));

        jobSchedule.setTriggerCron(schedule.getTriggerCron());
        jobSchedule.setActiveYn(schedule.getActiveYn());

        repository.flush();

        try {
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobSchedule.getId()).withSchedule(CronScheduleBuilder.cronSchedule(jobSchedule.getTriggerCron())).build();
            scheduler.rescheduleJob(new TriggerKey(jobSchedule.getId()), trigger);
        } catch (Exception e) {
            throw new ApiException(ApiExceptionType.FAILED_SCHEDULER, "수정");
        }
    }
}
