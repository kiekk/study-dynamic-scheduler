package com.example.studydynamicschedulerv2.dto;

import com.example.studydynamicschedulerv2.entity.scheduler.ProductScheduler;
import com.example.studydynamicschedulerv2.util.ModelMapperUtils;
import jakarta.validation.constraints.NotNull;

public class ProductSchedulerForm {

    private String id;

    @NotNull(message = "필수 항목입니다.")
    private String name;

    @NotNull(message = "필수 항목입니다.")
    private String productId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductScheduler toEntity() {
        return ModelMapperUtils.getModelMapper().map(this, ProductScheduler.class);
    }
}
