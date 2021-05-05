package com.tacx.activity.port.adapter.assembler;

import com.tacx.activity.domain.Record;
import com.tacx.activity.port.adapter.binding.RecordBinding;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

@Component
public class RecordAssembler implements Function<RecordBinding, Record> {
    @Override
    public Record apply(RecordBinding recordBinding) {
        return new Record(UUID.randomUUID(),
                LocalDateTime.parse(recordBinding.getTime()),
                recordBinding.getDistance(),
                recordBinding.getPower(),
                recordBinding.getCadence());
    }
}
