package com.tacx.activity.domain;

import com.tacx.activity.domain.exception.ActivityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public void delete(String id) {
        UUID uuid = UUID.fromString(id);
        activityRepository.remove(uuid);
    }

    public List<Activity> findActivities() {
        return activityRepository.findAll();
    }


    public Activity findActivity(String id) {
        UUID uuid = UUID.fromString(id);

        return activityRepository.findByActivityId(uuid)
                .orElseThrow(ActivityNotFoundException::new);
    }

    public void save(Activity activity) {
        activityRepository.save(activity);
    }

    public Integer getTotalDistance(List<Record> records) {
        return records.stream()
                .map(Record::getDistance)
                .mapToInt(r -> r).max().orElse(0);
    }

    public long getTotalDuration(List<Record> records) {
        LocalDateTime endTime = records.stream()
                .map(Record::getTime)
                .max(LocalDateTime::compareTo).orElse(LocalDateTime.MIN);

        LocalDateTime startTime = records.stream()
                .map(Record::getTime)
                .min(LocalDateTime::compareTo).orElse(LocalDateTime.MIN);

        return Duration.between(startTime, endTime).toMinutes();
    }

    public Double getAvrPower(List<Record> records) {
        return records.stream()
                .map(Record::getPower)
                .mapToInt(r -> r).average().orElse(0);
    }

    public Double getAvrCadence(List<Record> records) {
        return records.stream()
                .map(Record::getCadence)
                .mapToInt(r -> r).average().orElse(0);
    }
}
