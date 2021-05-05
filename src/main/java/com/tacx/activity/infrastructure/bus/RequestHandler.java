package com.tacx.activity.infrastructure.bus;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public interface RequestHandler<R extends Request, T> {

    T handle(R request) throws IOException, CsvValidationException;
}
