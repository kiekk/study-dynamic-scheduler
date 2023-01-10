package com.example.studydynamicschedulerv2.entity.scheduler;

import com.example.studydynamicschedulerv2.dto.ProductSchedulerForm;
import com.example.studydynamicschedulerv2.entity.BaseEntity;
import com.example.studydynamicschedulerv2.entity.Product;
import com.example.studydynamicschedulerv2.enums.scheduler.ExecuteType;
import com.example.studydynamicschedulerv2.util.ModelMapperUtils;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class ProductScheduler extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String name;

    private boolean enabled;

    private ExecuteType executeType;

    private String expression;


    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ExecuteType getExecuteType() {
        return executeType;
    }

    public void setExecuteType(ExecuteType executeType) {
        this.executeType = executeType;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void updateFields(ProductSchedulerForm productSchedulerForm) {
        ModelMapperUtils.getModelMapper().map(productSchedulerForm, this);
        setUpdateDate(LocalDateTime.now());
    }

    public void resume() {
        enabled = true;
    }

    public void pause() {
        enabled = false;
    }
}
