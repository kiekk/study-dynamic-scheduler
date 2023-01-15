package com.example.studydynamicschedulerv1.repository;

import com.example.studydynamicschedulerv1.entity.JobSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobScheduleRepository extends JpaRepository<JobSchedule, String> {
}
