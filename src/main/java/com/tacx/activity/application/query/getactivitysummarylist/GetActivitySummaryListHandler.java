package com.tacx.activity.application.query.getactivitysummarylist;

import com.tacx.activity.application.query.shared.assembler.ActivitySummaryDtoAssembler;
import com.tacx.activity.application.query.shared.BaseSummaryHandler;
import com.tacx.activity.application.query.shared.dto.ActivitySummaryDto;
import com.tacx.activity.application.query.shared.dto.RecordsSummaryDto;
import com.tacx.activity.domain.Activity;
import com.tacx.activity.domain.ActivityService;
import com.tacx.activity.infrastructure.bus.RequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GetActivitySummaryListHandler extends BaseSummaryHandler implements RequestHandler<GetActivitySummaryListQuery, GetActivitySummaryListResponse> {

    private final ActivitySummaryDtoAssembler activitySummaryDtoAssembler;

    public GetActivitySummaryListHandler(ActivityService activityService, ActivitySummaryDtoAssembler activitySummaryDtoAssembler) {
        super(activityService);
        this.activitySummaryDtoAssembler = activitySummaryDtoAssembler;
    }

    @Override
    public GetActivitySummaryListResponse handle(GetActivitySummaryListQuery request) {
        log.info("Handling get-activity-summary-list request");
        List<Activity> activities = activityService.findActivities();

        List<ActivitySummaryDto> activitySummaries = activities.stream()
                .map(activity -> {
                    RecordsSummaryDto recordsSummaryDto = prepareSummaryData(activity.getRecords());
                    return activitySummaryDtoAssembler.apply(activity, recordsSummaryDto);
                })
                .collect(Collectors.toList());

        return new GetActivitySummaryListResponse(activitySummaries);
    }
}
