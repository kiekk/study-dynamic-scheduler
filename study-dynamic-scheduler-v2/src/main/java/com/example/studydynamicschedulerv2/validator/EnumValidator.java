package com.example.studydynamicschedulerv2.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {
    private ValidEnum annotation;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();

        if (value == null) {
            return annotation.allowNull();
        }

        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (Objects.equals(value, enumValue.toString())) {
                    return true;
                }
            }
        }
        return false;
    }
}