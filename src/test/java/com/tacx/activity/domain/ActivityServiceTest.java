package com.tacx.activity.domain;

import com.tacx.activity.domain.exception.ActivityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    private ActivityService activityService;

    @Mock
    private ActivityRepository activityRepository;

    @BeforeEach
    public void setUp() {
        activityService = new ActivityService(activityRepository);
    }

    @Test
    void should_delete_activity() {
        //given
        String id = UUID.randomUUID().toString();
        UUID uuid = UUID.fromString(id);

        //when
        activityService.delete(id);

        //then
        verify(activityRepository).remove(uuid);
    }

    @Test
    void should_findActivities() {
        //when
        List<Activity> activities = activityService.findActivities();

        //then
        assertThat(activities).isNotNull();
        verify(activityRepository).findAll();
    }

    @Test
    void should_findActivity_return_activity() {
        //given
        String id = UUID.randomUUID().toString();
        UUID uuid = UUID.fromString(id);

        Activity expectedActivity = mock(Activity.class);
        when(activityRepository.findByActivityId(uuid)).thenReturn(Optional.of(expectedActivity));

        //when
        Activity activity = activityService.findActivity(id);
        assertThat(activity).isEqualTo(expectedActivity);
    }

    @Test
    void should_findActivity_throw_ActivityNotFoundException_when_activityNotExist() {
        //given
        String id = UUID.randomUUID().toString();

        //when
        Throwable throwable = catchThrowable(() -> activityService.findActivity(id));

        //then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(ActivityNotFoundException.class);
    }

    @Test
    void should_save_activity() {
        //given
        Activity activity = mock(Activity.class);

        //when
        activityService.save(activity);

        //then
        verify(activityRepository).save(activity);
    }

    @Test
    void should_get_totalDistance() {
        //given
        List<Record> records = prepareRecords();

        //when
        Integer distance = activityService.getTotalDistance(records);

        //then
        assertThat(distance).isEqualTo(40);
    }

    @Test
    void should_get_totalDuration() {
        //given
        List<Record> records = prepareRecords();

        //when
        long duration = activityService.getTotalDuration(records);

        //then
        assertThat(duration).isEqualTo(59L);
    }

    @Test
    void should_get_averagePower() {
        //given
        List<Record> records = prepareRecords();

        //when
        Double power = activityService.getAvrPower(records);

        //then
        assertThat(power).isEqualTo(35);
    }

    @Test
    void should_get_averageCadence() {
        //given
        List<Record> records = prepareRecords();

        //when
        Double cadence = activityService.getAvrCadence(records);

        //then
        assertThat(cadence).isEqualTo(22.5);
    }

    @NotNull
    private List<Record> prepareRecords() {
        Record record1 = prepareRecord(LocalDateTime.now(), 10, 20, 30);
        Record record2 = prepareRecord(LocalDateTime.now().minusHours(1), 40, 50, 15);
        return Arrays.asList(record1, record2);
    }

    private Record prepareRecord(LocalDateTime duration, Integer distance, Integer power, Integer cadence) {
        return new Record(UUID.randomUUID(),
                duration,
                distance,
                power,
                cadence);
    }
}
