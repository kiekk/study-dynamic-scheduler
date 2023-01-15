package com.example.studydynamicschedulerv1.enums;

public class EnumMapperValue {

    private final String code;
    private final String name;

    public EnumMapperValue(EnumMapperType mapperType) {
        code = mapperType.getCode();
        name = mapperType.getName();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{"
            + "code:" + code
            + ","
            + "name:" + name
            +
            "}";
    }
}
