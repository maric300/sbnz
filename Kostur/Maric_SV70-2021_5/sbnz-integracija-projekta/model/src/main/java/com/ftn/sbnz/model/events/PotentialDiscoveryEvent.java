package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

// Definišemo klasu kao event za CEP
@Role(Role.Type.EVENT)
// Definišemo koje polje predstavlja vremensku oznaku događaja
@Timestamp("timestamp")
public class PotentialDiscoveryEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date timestamp;
    private Long mineralId;
    private String mineralName;
    private String location;
    private Long userId;

    public PotentialDiscoveryEvent() {
        super();
    }

    public PotentialDiscoveryEvent(Long mineralId, String mineralName, String location, Long userId) {
        super();
        this.timestamp = new Date();
        this.mineralId = mineralId;
        this.mineralName = mineralName;
        this.location = location;
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getMineralId() {
        return mineralId;
    }

    public void setMineralId(Long mineralId) {
        this.mineralId = mineralId;
    }

    public String getMineralName() {
        return mineralName;
    }

    public void setMineralName(String mineralName) {
        this.mineralName = mineralName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
