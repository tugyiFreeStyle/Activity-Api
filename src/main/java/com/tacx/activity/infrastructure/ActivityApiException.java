package com.tacx.activity.infrastructure;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ActivityApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private String errorMessage;

    public ActivityApiException(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
