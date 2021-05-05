

package com.tacx.activity.application.shared.assembler;

import com.tacx.activity.application.query.shared.assembler.ActivitySummaryDtoAssembler;
import com.tacx.activity.application.query.shared.dto.ActivitySummaryDto;
import com.tacx.activity.application.query.shared.dto.RecordsSummaryDto;
import com.tacx.activity.domain.Activity;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ActivitySummaryAssemblerTest {

    private ActivitySummaryDtoAssembler activitySummaryDtoAssembler = new ActivitySummaryDtoAssembler();

    @Test
    void should_assemble_activitySummaryDto() {
        //given
        Activity activity = mock(Activity.class);
        RecordsSummaryDto recordsSummaryDto = mock(RecordsSummaryDto.class);

        //when
        ActivitySummaryDto activitySummary = activitySummaryDtoAssembler.apply(activity, recordsSummaryDto);

        //then
        assertThat(activitySummary.getDistance()).isEqualTo(recordsSummaryDto.getDistance());
        assertThat(activitySummary.getPower()).isEqualTo(recordsSummaryDto.getPower());
        assertThat(activitySummary.getCadence()).isEqualTo(recordsSummaryDto.getCadence());
        assertThat(activitySummary.getDuration()).isEqualTo(recordsSummaryDto.getDuration());

        assertThat(activitySummary.getStartTime()).isEqualTo(activity.getStartTime());
        assertThat(activitySummary.getType()).isEqualTo(activity.getType());
        assertThat(activitySummary.getName()).isEqualTo(activity.getName());
        assertThat(activitySummary.getId()).isEqualTo(activity.getId());
    }
}

