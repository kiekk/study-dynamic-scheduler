package com.example.studydynamicschedulerv2.repository.product;

import com.example.studydynamicschedulerv2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
