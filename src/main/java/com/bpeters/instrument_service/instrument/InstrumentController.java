package com.bpeters.instrument_service.instrument;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    private final InstrumentService service;

    public InstrumentController(InstrumentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Instrument> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Instrument getById(@PathVariable Long id) {
        return service.getById(id);
    }
}