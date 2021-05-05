package com.tacx.activity.application.query.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordsSummaryDto {

    @JsonProperty("total distance")
    private Integer distance;

    @JsonProperty("duration(minutes)")
    private Long duration;

    @JsonProperty("average power")
    private Double power;

    @JsonProperty("average cadence")
    private Double cadence;
}
