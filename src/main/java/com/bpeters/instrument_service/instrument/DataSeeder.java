package com.bpeters.instrument_service.instrument;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final InstrumentRepository repository;

    public DataSeeder(InstrumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                new Instrument("FED-CUT-DEC26", "Fed cuts rates at December 2026 meeting", "ACTIVE"),
                new Instrument("CPI-NOV26-ABOVE3", "November 2026 CPI above 3%", "ACTIVE"),
                new Instrument("NFL-WK7-KC-WIN", "Kansas City wins Week 7 game", "SETTLED")
            ));
        }
    }
}
