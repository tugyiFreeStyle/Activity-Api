package com.tacx.activity.port;

import com.opencsv.exceptions.CsvValidationException;
import com.tacx.activity.domain.Activity;

import java.io.IOException;
import java.io.Reader;

public interface ActivityFileReader {

    Activity read(Reader reader) throws IOException, CsvValidationException;
}
