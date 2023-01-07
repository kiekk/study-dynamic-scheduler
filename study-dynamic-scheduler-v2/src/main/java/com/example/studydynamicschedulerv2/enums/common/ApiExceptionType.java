package com.example.studydynamicschedulerv2.enums.common;

import org.springframework.http.HttpStatus;

public enum ApiExceptionType {
    FAILED_TO_FETCH(HttpStatus.BAD_REQUEST, "요청 데이터와 일치하는 #0 정보가 존재하지 않습니다.");

    private final HttpStatus status;

    private final String message;

    ApiExceptionType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
