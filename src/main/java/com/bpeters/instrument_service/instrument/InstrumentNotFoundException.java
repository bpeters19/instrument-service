package com.bpeters.instrument_service.instrument;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstrumentNotFoundException extends RuntimeException {
    public InstrumentNotFoundException(Long id) {
        super("Instrument " + id + " not found");
    }
}