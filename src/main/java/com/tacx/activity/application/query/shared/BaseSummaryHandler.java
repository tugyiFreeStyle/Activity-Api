package com.tacx.activity.application.query.shared;

import com.tacx.activity.application.query.shared.dto.RecordsSummaryDto;
import com.tacx.activity.domain.ActivityService;
import com.tacx.activity.domain.Record;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class BaseSummaryHandler {

    protected final ActivityService activityService;

    public RecordsSummaryDto prepareSummaryData(List<Record> records) {

        RecordsSummaryDto recordsSummaryDto = new RecordsSummaryDto();
        recordsSummaryDto.setCadence(activityService.getAvrCadence(records));
        recordsSummaryDto.setDistance(activityService.getTotalDistance(records));
        recordsSummaryDto.setPower(activityService.getAvrPower(records));
        recordsSummaryDto.setDuration(activityService.getTotalDuration(records));
        return recordsSummaryDto;
    }
}
