package com.tacx.activity.application.query.getactivitysummarylist;

import com.flextrade.jfixture.JFixture;
import com.tacx.activity.application.query.shared.assembler.ActivitySummaryDtoAssembler;
import com.tacx.activity.domain.Activity;
import com.tacx.activity.domain.ActivityService;
import com.tacx.activity.domain.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class GetActivitySummaryListHandlerTest {

    private GetActivitySummaryListHandler getActivitySummaryListHandler;

    @Mock
    private ActivityService activityService;


    @BeforeEach
    public void setUp() {
        getActivitySummaryListHandler = new GetActivitySummaryListHandler(
                activityService, new ActivitySummaryDtoAssembler());
    }

    @Test
    void should_handle_getActivitySummaryListQuery() {
        //given
        Activity activity1 = prepareActivity(Collections.singletonList(mock(Record.class)));
        Activity activity2 = prepareActivity(Collections.singletonList(mock(Record.class)));

        List<Activity> activities = Arrays.asList(activity1, activity2);

        when(activityService.findActivities()).thenReturn(activities);

        when(activityService.getAvrCadence(activities.get(0).getRecords())).thenReturn(any());
        when(activityService.getAvrPower(activities.get(0).getRecords())).thenReturn(any());
        when(activityService.getTotalDistance(activities.get(0).getRecords())).thenReturn(any());
        when(activityService.getTotalDuration(activities.get(0).getRecords())).thenReturn(5L);

        when(activityService.getAvrCadence(activities.get(1).getRecords())).thenReturn(any());
        when(activityService.getAvrPower(activities.get(1).getRecords())).thenReturn(any());
        when(activityService.getTotalDistance(activities.get(1).getRecords())).thenReturn(any());
        when(activityService.getTotalDuration(activities.get(1).getRecords())).thenReturn(5L);

        //when
        GetActivitySummaryListResponse summaryListResponse = getActivitySummaryListHandler.handle(new GetActivitySummaryListQuery());

        //then
        assertThat(summaryListResponse.getActivities()).isNotEmpty();
        assertThat(summaryListResponse.getActivities().size()).isEqualTo(2);
    }

    private Activity prepareActivity(List<Record> records) {
        JFixture jFixture = new JFixture();
        jFixture.customise()
                .propertyOf(Activity.class, "records", records);
        return jFixture.create(Activity.class);
    }
}
