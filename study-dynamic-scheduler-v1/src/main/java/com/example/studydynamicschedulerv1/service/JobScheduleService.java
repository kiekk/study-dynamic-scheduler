package com.example.studydynamicschedulerv1.service;

import com.example.studydynamicschedulerv1.entity.JobSchedule;
import com.example.studydynamicschedulerv1.enums.common.ApiExceptionType;
import com.example.studydynamicschedulerv1.exception.ApiException;
import com.example.studydynamicschedulerv1.repository.JobScheduleRepository;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
}
