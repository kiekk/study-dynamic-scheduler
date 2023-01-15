package com.example.studydynamicschedulerv1.batch.runner;

import com.example.studydynamicschedulerv1.batch.job.ScheduleJob1;
import com.example.studydynamicschedulerv1.batch.job.ScheduleJob2;
import com.example.studydynamicschedulerv1.entity.JobSchedule;
import com.example.studydynamicschedulerv1.service.JobScheduleService;
import org.quartz.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobScheduleJobRunner extends JobRunner {
    private final JobScheduleService jobScheduleService;
    private final Scheduler scheduler;

    public JobScheduleJobRunner(JobScheduleService jobScheduleService, Scheduler scheduler) {
        this.jobScheduleService = jobScheduleService;
        this.scheduler = scheduler;
    }

    @Override
    protected void doRun(ApplicationArguments args) {

        // 활성화 Y인 job 목록 가져오기
        List<JobSchedule> jobScheduleList = jobScheduleService.search().stream().filter(JobSchedule::isActive).toList();

        jobScheduleList.forEach(schedule -> {
            JobDataMap jobDataMap = new JobDataMap();
            JobDetail jobDetail;
            if ("batchJob1".equals(schedule.getJobName())) {
                jobDetail = JobBuilder.newJob(ScheduleJob1.class).withIdentity(schedule.getJobName()).usingJobData(jobDataMap).build();
            } else {
                jobDetail = JobBuilder.newJob(ScheduleJob2.class).withIdentity(schedule.getJobName()).usingJobData(jobDataMap).build();
            }

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(schedule.getId()).withSchedule(CronScheduleBuilder.cronSchedule(schedule.getTriggerCron())).build();

            try {
                scheduler.scheduleJob(jobDetail, trigger);
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
