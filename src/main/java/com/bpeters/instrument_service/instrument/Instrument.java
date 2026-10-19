package com.bpeters.instrument_service.instrument;

import jakarta.persistence.*;

@Entity
@Table(name = "instruments")
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String symbol;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    protected Instrument() {} // required by JPA

    public Instrument(String symbol, String name, String status) {
        this.symbol = symbol;
        this.name = name;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getSymbol() { return symbol; }
    public String getName() { return name; }
    public String getStatus() { return status; }
}
