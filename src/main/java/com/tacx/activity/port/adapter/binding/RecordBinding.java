package com.tacx.activity.port.adapter.binding;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordBinding {

    @CsvBindByPosition(position = 1)
    private String time;

    @CsvBindByPosition(position = 2)
    private Integer distance;

    @CsvBindByPosition(position = 3)
    private Integer power;

    @CsvBindByPosition(position = 4)
    private Integer cadence;
}
