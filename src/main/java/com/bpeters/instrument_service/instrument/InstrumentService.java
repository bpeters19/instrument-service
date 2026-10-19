package com.bpeters.instrument_service.instrument;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InstrumentService {

    private final InstrumentRepository repository;

    public InstrumentService(InstrumentRepository repository) {
        this.repository = repository;
    }

    public List<Instrument> getAll() {
        return repository.findAll();
    }

    public Instrument getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new InstrumentNotFoundException(id));
    }
}