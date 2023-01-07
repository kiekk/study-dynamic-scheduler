package com.example.studydynamicschedulerv2.util;

import org.modelmapper.ModelMapper;

public class ModelMapperUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    private ModelMapperUtils() {

    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

}
