package com.example.studydynamicschedulerv1.service;

import com.example.studydynamicschedulerv1.entity.JobSchedule;
import com.example.studydynamicschedulerv1.repository.JobScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobScheduleService {

    private final JobScheduleRepository repository;

    public JobScheduleService(JobScheduleRepository repository) {
        this.repository = repository;
    }

    public List<JobSchedule> search() {
        return repository.findAll();
    }

}
