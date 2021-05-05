package com.tacx.activity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Activity {

    private UUID id;
    private String name;
    private String type;
    private LocalDateTime startTime;
    private List<Record> records;
}
