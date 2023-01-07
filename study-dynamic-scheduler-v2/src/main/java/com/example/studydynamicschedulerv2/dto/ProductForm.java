package com.example.studydynamicschedulerv2.dto;

import com.example.studydynamicschedulerv2.entity.Product;
import com.example.studydynamicschedulerv2.util.ModelMapperUtils;

public class ProductForm {
    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product toEntity() {
        return ModelMapperUtils.getModelMapper().map(this, Product.class);
    }
}
