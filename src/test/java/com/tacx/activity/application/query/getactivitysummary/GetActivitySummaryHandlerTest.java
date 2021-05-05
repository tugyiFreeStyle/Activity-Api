package com.tacx.activity.application.query.getactivitysummary;

import com.flextrade.jfixture.JFixture;
import com.tacx.activity.application.query.shared.assembler.ActivitySummaryDtoAssembler;
import com.tacx.activity.application.query.shared.dto.ActivitySummaryDto;
import com.tacx.activity.domain.Activity;
import com.tacx.activity.domain.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class GetActivitySummaryHandlerTest {

    private GetActivitySummaryHandler getActivitySummaryHandler;

    @Mock
    private ActivityService activityService;

    @BeforeEach
    public void setUp() {
        getActivitySummaryHandler = new GetActivitySummaryHandler(
                activityService, new ActivitySummaryDtoAssembler());
    }

    @Test
    void should_handle_getActivitySummaryQuery() {
        //given
        GetActivitySummaryQuery summaryQuery = new GetActivitySummaryQuery(UUID.randomUUID().toString());
        Activity activity = new JFixture().create(Activity.class);

        when(activityService.findActivity(summaryQuery.getActivityId())).thenReturn(activity);
        when(activityService.getAvrCadence(activity.getRecords())).thenReturn(5d);
        when(activityService.getAvrPower(activity.getRecords())).thenReturn(100d);
        when(activityService.getTotalDistance(activity.getRecords())).thenReturn(200);
        when(activityService.getTotalDuration(activity.getRecords())).thenReturn(5L);

        //when
        GetActivitySummaryResponse summaryResponse = getActivitySummaryHandler.handle(summaryQuery);

        //then
        ActivitySummaryDto activitySummary = summaryResponse.getActivity();
        assertThat(activitySummary.getId()).isEqualTo(activity.getId());
        assertThat(activitySummary.getName()).isEqualTo(activity.getName());
        assertThat(activitySummary.getType()).isEqualTo(activity.getType());
        assertThat(activitySummary.getStartTime()).isEqualTo(activity.getStartTime());

        assertThat(activitySummary.getPower()).isEqualTo(100d);
        assertThat(activitySummary.getCadence()).isEqualTo(5d);
        assertThat(activitySummary.getDistance()).isEqualTo(200);
    }
}
