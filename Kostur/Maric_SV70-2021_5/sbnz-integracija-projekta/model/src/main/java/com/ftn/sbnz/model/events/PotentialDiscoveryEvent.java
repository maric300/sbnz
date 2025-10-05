package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID; // ISPRAVKA: Importujemo UUID

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class PotentialDiscoveryEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date timestamp;

    // ISPRAVKA: Tip je sada UUID
    private UUID mineralId;

    private String mineralName;
    private String location;
    private UUID userId;

    public PotentialDiscoveryEvent() {
        this.timestamp = new Date();
    }

    // ISPRAVKA: Konstruktor sada prihvata UUID
    public PotentialDiscoveryEvent(UUID mineralId, String mineralName, String location, UUID userId) {
        this.timestamp = new Date();
        this.mineralId = mineralId;
        this.mineralName = mineralName;
        this.location = location;
        this.userId = userId;
    }

    // --- Getteri i Setteri ---

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public UUID getMineralId() { return mineralId; }
    public void setMineralId(UUID mineralId) { this.mineralId = mineralId; }
    public String getMineralName() { return mineralName; }
    public void setMineralName(String mineralName) { this.mineralName = mineralName; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
}