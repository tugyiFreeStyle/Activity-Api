package com.tacx.activity.application.query.getactivitysummary;

import com.tacx.activity.application.query.shared.BaseSummaryHandler;
import com.tacx.activity.application.query.shared.assembler.ActivitySummaryDtoAssembler;
import com.tacx.activity.application.query.shared.dto.ActivitySummaryDto;
import com.tacx.activity.application.query.shared.dto.RecordsSummaryDto;
import com.tacx.activity.domain.Activity;
import com.tacx.activity.domain.ActivityService;
import com.tacx.activity.infrastructure.bus.RequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetActivitySummaryHandler extends BaseSummaryHandler implements RequestHandler<GetActivitySummaryQuery, GetActivitySummaryResponse> {

    private final ActivitySummaryDtoAssembler activitySummaryDtoAssembler;

    public GetActivitySummaryHandler(ActivityService activityService, ActivitySummaryDtoAssembler activitySummaryDtoAssembler) {
        super(activityService);
        this.activitySummaryDtoAssembler = activitySummaryDtoAssembler;
    }

    @Override
    public GetActivitySummaryResponse handle(GetActivitySummaryQuery request) {
        log.info("Handling get-activity-summary request with activityId: {}", request.getActivityId());
        Activity activity = activityService.findActivity(request.getActivityId());

        RecordsSummaryDto recordsSummaryDto = prepareSummaryData(activity.getRecords());
        ActivitySummaryDto activitySummary = activitySummaryDtoAssembler.apply(activity, recordsSummaryDto);
        return new GetActivitySummaryResponse(activitySummary);
    }
}
