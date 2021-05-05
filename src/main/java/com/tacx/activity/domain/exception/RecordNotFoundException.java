package com.tacx.activity.domain.exception;

import com.tacx.activity.infrastructure.ActivityApiException;
import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends ActivityApiException {
    public RecordNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Record not found.");
    }
}
