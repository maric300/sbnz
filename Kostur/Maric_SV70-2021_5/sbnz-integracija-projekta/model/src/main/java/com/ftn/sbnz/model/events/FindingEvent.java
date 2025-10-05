package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class FindingEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date timestamp;
    private UUID mineralId;
    private String location;

    public FindingEvent(UUID mineralId, String location) {
        this.timestamp = new Date();
        this.mineralId = mineralId;
        this.location = location;
    }

    //fake construcor for testing
    public FindingEvent(UUID mineralId, String location, Date timestamp) {
        this.timestamp = timestamp;
        this.mineralId = mineralId;
        this.location = location;
    }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public UUID getMineralId() { return mineralId; }
    public void setMineralId(UUID mineralId) { this.mineralId = mineralId; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}