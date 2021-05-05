package com.tacx.activity.application.query.getactivitysummary;

import com.tacx.activity.application.query.shared.dto.ActivitySummaryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetActivitySummaryResponse {

    private ActivitySummaryDto activity;
}
