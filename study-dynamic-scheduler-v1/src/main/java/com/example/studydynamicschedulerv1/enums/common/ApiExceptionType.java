package com.example.studydynamicschedulerv1.enums.common;

import org.springframework.http.HttpStatus;

public enum ApiExceptionType {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "#0 항목은 #1"),
    FAILED_SCHEDULER(HttpStatus.BAD_REQUEST, "Scheduler #1에 실패했습니다."),
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
