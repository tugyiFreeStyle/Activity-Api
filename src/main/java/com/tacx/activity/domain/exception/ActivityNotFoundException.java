package com.tacx.activity.domain.exception;

import com.tacx.activity.infrastructure.ActivityApiException;
import org.springframework.http.HttpStatus;

public class ActivityNotFoundException extends ActivityApiException {
    public ActivityNotFoundException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Activity not found.");
    }
}
