package com.tacx.activity.port.adapter;

import com.tacx.activity.domain.Activity;
import com.tacx.activity.domain.Record;
import com.tacx.activity.port.adapter.assembler.ActivityAssembler;
import com.tacx.activity.port.adapter.assembler.RecordAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ActivityCsvFileReaderTest {

    private ActivityAssembler activityAssembler = new ActivityAssembler(new RecordAssembler());

    private ActivityCsvFileReader activityCsvFileReader;

    @BeforeEach
    public void setUp() {
        activityCsvFileReader = new ActivityCsvFileReader(activityAssembler);
    }

    @Test
    void should_read_csvFile() {
        //given
        String csv = "activity_def,name,type,start_time,\n" +
                "activity,My first ride,cycling,2011-12-03T10:15:00,\n" +
                "record_def,time,distance,power,cadence\n" +
                "record,2011-12-03T10:15:00,0,150,92\n" +
                "record,2011-12-03T10:16:00,300,150,75\n" +
                "record,2011-12-03T10:17:00,600,150,85\n" +
                "record,2011-12-03T10:18:00,850,180,90\n" +
                "record,2011-12-03T10:19:00,1100,180,87\n" +
                "record,2011-12-03T10:20:00,1380,180,96\n" +
                "record,2011-12-03T10:21:00,1610,150,91\n" +
                "record,2011-12-03T10:22:00,1890,150,87\n" +
                "record,2011-12-03T10:23:00,2150,150,89\n" +
                "record,2011-12-03T10:24:00,2410,180,83";

        StringReader reader = new StringReader(csv);

        //when
        Activity activity = activityCsvFileReader.read(reader);
        reader.close();

        //then
        assertThat(activity.getId()).isNotNull();
        assertThat(activity.getName()).isEqualTo("My first ride");
        assertThat(activity.getType()).isEqualTo("cycling");
        assertThat(activity.getStartTime()).hasToString("2011-12-03T10:15");

        List<Record> records = activity.getRecords();
        assertThat(records).hasSize(10);

        Record record = activity.getRecords().get(0);
        assertThat(record.getTime().toString()).hasToString("2011-12-03T10:15");
        assertThat(record.getDistance()).isEqualTo(0);
        assertThat(record.getPower()).isEqualTo(150);
        assertThat(record.getCadence()).isEqualTo(92);
    }
}
