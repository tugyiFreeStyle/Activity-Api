package com.tacx.activity.application.query.getactivitysummarylist;

import com.tacx.activity.application.query.shared.dto.ActivitySummaryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetActivitySummaryListResponse {

    List<ActivitySummaryDto> activities;
}
