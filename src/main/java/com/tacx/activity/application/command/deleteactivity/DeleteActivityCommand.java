package com.tacx.activity.application.command.deleteactivity;

import com.tacx.activity.infrastructure.bus.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class DeleteActivityCommand implements Request {

    @NotBlank(message = "activity id required")
    private String activityId;
}
