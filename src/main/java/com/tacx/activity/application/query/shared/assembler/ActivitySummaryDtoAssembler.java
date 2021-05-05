package com.tacx.activity.application.query.shared.assembler;

import com.tacx.activity.application.query.shared.dto.ActivitySummaryDto;
import com.tacx.activity.application.query.shared.dto.RecordsSummaryDto;
import com.tacx.activity.domain.Activity;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class ActivitySummaryDtoAssembler implements BiFunction<Activity, RecordsSummaryDto, ActivitySummaryDto> {
    @Override
    public ActivitySummaryDto apply(Activity activity, RecordsSummaryDto recordsSummaryDto) {
        ActivitySummaryDto activitySummaryDto = new ActivitySummaryDto();
        activitySummaryDto.setId(activity.getId());
        activitySummaryDto.setName(activity.getName());
        activitySummaryDto.setStartTime(activity.getStartTime());
        activitySummaryDto.setType(activity.getType());

        activitySummaryDto.setPower(recordsSummaryDto.getPower());
        activitySummaryDto.setCadence(recordsSummaryDto.getCadence());
        activitySummaryDto.setDistance(recordsSummaryDto.getDistance());
        activitySummaryDto.setDuration(recordsSummaryDto.getDuration());
        return activitySummaryDto;
    }
}
