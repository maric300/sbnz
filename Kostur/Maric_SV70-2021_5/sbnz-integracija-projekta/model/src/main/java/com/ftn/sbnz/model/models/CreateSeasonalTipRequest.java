package com.ftn.sbnz.model.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Predstavlja zahtev iz CEP pravila da se kreira novi sezonski savet u bazi.
 */
public class CreateSeasonalTipRequest implements Serializable {

    private UUID mineralId;
    private String location;
    private String message;

    public CreateSeasonalTipRequest(UUID mineralId, String location, String message) {
        this.mineralId = mineralId;
        this.location = location;
        this.message = message;
    }

    // Getteri
    public UUID getMineralId() { return mineralId; }
    public String getLocation() { return location; }
    public String getMessage() { return message; }
}