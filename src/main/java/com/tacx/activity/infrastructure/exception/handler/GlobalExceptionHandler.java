package com.tacx.activity.infrastructure.exception.handler;


import com.tacx.activity.infrastructure.ActivityApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error("Exception class: {}, message: {}", e.getClass().getName(), e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(ActivityApiException.class)
    public ResponseEntity<Object> handleBaseException(ActivityApiException e) {
        log.error("Exception class: {}, key: {}", e.getClass().getSimpleName(), e.getErrorMessage(), e);
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(e.getErrorMessage());
    }
}

