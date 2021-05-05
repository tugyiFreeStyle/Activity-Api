package com.tacx.activity.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository {

    Optional<Activity> findByActivityId(final UUID id);

    List<Activity> findAll();

    void remove(final UUID id);

    void save(Activity activity);
}
