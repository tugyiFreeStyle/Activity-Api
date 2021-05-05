package com.tacx.activity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Record {

    private UUID id;
    private LocalDateTime time;
    private Integer distance;
    private Integer power;
    private Integer cadence;
}
