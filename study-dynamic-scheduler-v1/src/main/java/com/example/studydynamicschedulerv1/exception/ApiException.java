package com.example.studydynamicschedulerv1.exception;

import com.example.studydynamicschedulerv1.enums.common.ApiExceptionType;

public class ApiException extends Exception {

    private final ApiExceptionType type;

    private String[] args;

    public ApiException(ApiExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }

    public ApiException(ApiExceptionType type, String... args) {
        super(replaceArgs(type.getMessage(), args));
        this.type = type;
        this.args = args;
    }

    public ApiExceptionType getType() {
        return type;
    }

    public String[] getArgs() {
        return args;
    }

    public static String replaceArgs(String errorMessage, String[] replaceArgs) {
        for (int index = 0; index < replaceArgs.length; index++) {
            String replaceArg = replaceArgs[index];
            errorMessage = errorMessage.replace("#" + index, replaceArg);
        }
        return errorMessage;
    }

}
