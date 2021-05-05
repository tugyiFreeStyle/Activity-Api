package com.tacx.activity.application.query.getactivitysummary;

import com.tacx.activity.infrastructure.bus.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class GetActivitySummaryQuery implements Request {

    @NotBlank(message = "activity id required")
    private String activityId;
}
