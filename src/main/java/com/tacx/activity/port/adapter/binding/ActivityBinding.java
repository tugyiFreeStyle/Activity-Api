package com.tacx.activity.port.adapter.binding;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActivityBinding {
    private String name;
    private String type;
    private LocalDateTime startTime;
}
