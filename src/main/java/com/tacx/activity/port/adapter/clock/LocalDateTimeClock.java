package com.tacx.activity.port.adapter.clock;

import com.tacx.activity.port.Clock;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class LocalDateTimeClock implements Clock {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
