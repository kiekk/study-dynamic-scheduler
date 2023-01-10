package com.example.studydynamicschedulerv2.dto;

import com.example.studydynamicschedulerv2.entity.scheduler.ProductScheduler;
import com.example.studydynamicschedulerv2.enums.scheduler.ExecuteType;
import com.example.studydynamicschedulerv2.util.ModelMapperUtils;
import com.example.studydynamicschedulerv2.validator.ValidEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductSchedulerForm {

    private String id;

    @NotNull(message = "필수 항목입니다.")
    private String name;

    @NotNull(message = "필수 항목입니다.")
    private String productId;

    @NotNull(message = "필수 항목입니다.")
    private boolean enabled;

    @NotEmpty(message = "필수 항목입니다.")
    @ValidEnum(enumClass = ExecuteType.class, message = "ExecuteType 형식이 아닙니다.")
    private String executeType;

    @NotEmpty(message = "필수 항목입니다.")
    private String expression;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getExecuteType() {
        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public ProductScheduler toEntity() {
        return ModelMapperUtils.getModelMapper().map(this, ProductScheduler.class);
    }
}
