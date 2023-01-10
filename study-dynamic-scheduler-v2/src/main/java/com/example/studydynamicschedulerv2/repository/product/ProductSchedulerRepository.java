package com.example.studydynamicschedulerv2.repository.product;

import com.example.studydynamicschedulerv2.entity.scheduler.ProductScheduler;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductSchedulerRepository extends JpaRepository<ProductScheduler, String> {

    @EntityGraph(attributePaths = {"product"})
    @Override
    List<ProductScheduler> findAll();

    @EntityGraph(attributePaths = {"product"})
    @Override
    Optional<ProductScheduler> findById(String id);
}
