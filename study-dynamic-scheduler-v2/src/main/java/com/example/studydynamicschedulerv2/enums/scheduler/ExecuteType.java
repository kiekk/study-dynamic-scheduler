package com.example.studydynamicschedulerv2.enums.scheduler;

import com.example.studydynamicschedulerv2.enums.EnumMapperType;

public enum ExecuteType implements EnumMapperType {
    CRON("CRON");


    private final String name;

    ExecuteType(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getName() {
        return name;
    }
}