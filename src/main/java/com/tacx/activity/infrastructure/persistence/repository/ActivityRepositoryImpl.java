package com.tacx.activity.infrastructure.persistence.repository;

import com.tacx.activity.domain.Activity;
import com.tacx.activity.domain.ActivityRepository;
import com.tacx.activity.domain.exception.RecordNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ActivityRepositoryImpl implements ActivityRepository {

    private final Map<UUID, Activity> memory;

    public ActivityRepositoryImpl() {
        memory = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Activity> findByActivityId(UUID id) {
        return Optional.ofNullable(memory.get(id));
    }

    @Override
    public List<Activity> findAll() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public void remove(UUID id) {
        try {
            memory.remove(id);
        } catch (NullPointerException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Activity activity) {
        memory.put(activity.getId(), activity);
    }
}
