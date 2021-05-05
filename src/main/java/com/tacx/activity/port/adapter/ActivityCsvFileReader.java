package com.tacx.activity.port.adapter;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tacx.activity.domain.Activity;
import com.tacx.activity.port.ActivityFileReader;
import com.tacx.activity.port.adapter.assembler.ActivityAssembler;
import com.tacx.activity.port.adapter.binding.ActivityBinding;
import com.tacx.activity.port.adapter.binding.RecordBinding;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class ActivityCsvFileReader implements ActivityFileReader {

    private final ActivityAssembler activityAssembler;

    @Override
    @SneakyThrows
    public Activity read(Reader reader) {

        try (CSVReader csvReader = new CSVReader(reader)) {
            csvReader.readNext(); //skip definition line

            String[] firstLine = csvReader.readNext(); //first line for activity
            ActivityBinding activityBinding = parse(firstLine);

            var csvToBeanBuilder = new CsvToBeanBuilder(csvReader);
            csvReader.readNext(); //skip record definition line

            List<RecordBinding> recordBindings = csvToBeanBuilder
                    .withType(RecordBinding.class)
                    .build()
                    .parse();

            return activityAssembler.apply(activityBinding, recordBindings);
        }
    }

    private ActivityBinding parse(String[] line) {
        ActivityBinding activityBinding = new ActivityBinding();
        activityBinding.setName(line[1]);
        activityBinding.setType(line[2]);
        activityBinding.setStartTime(LocalDateTime.parse(line[3]));

        return activityBinding;
    }
}




