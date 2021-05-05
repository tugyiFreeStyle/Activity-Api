package com.tacx.activity.port.adapter.assembler;

import com.tacx.activity.domain.Activity;
import com.tacx.activity.domain.Record;
import com.tacx.activity.port.adapter.binding.ActivityBinding;
import com.tacx.activity.port.adapter.binding.RecordBinding;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ActivityAssembler implements BiFunction<ActivityBinding, List<RecordBinding>, Activity> {

    private final RecordAssembler recordAssembler;

    @Override
    public Activity apply(ActivityBinding activityBinding, List<RecordBinding> recordBindings) {

        List<Record> records = recordBindings
                .stream()
                .map(recordAssembler)
                .collect(Collectors.toList());

        return new Activity(UUID.randomUUID(),
                activityBinding.getName(),
                activityBinding.getType(),
                activityBinding.getStartTime(),
                records);
    }
}
