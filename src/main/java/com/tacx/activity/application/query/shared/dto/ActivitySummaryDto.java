package com.tacx.activity.application.query.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ActivitySummaryDto {

    private UUID id;

    private String name;

    private String type;

    private LocalDateTime startTime;

    @JsonProperty("total distance")
    private Integer distance;

    @JsonProperty("duration(minutes)")
    private Long duration;

    @JsonProperty("average power")
    private Double power;

    @JsonProperty("average cadence")
    private Double cadence;
}
