package com.ftn.sbnz.model.events;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class HotspotAlertFired implements Serializable {

    private UUID mineralId;
    private String location;
    private String attributeValue;

    public HotspotAlertFired(UUID mineralId, String location, String attributeValue) {
        this.mineralId = mineralId;
        this.location = location;
        this.attributeValue = attributeValue;
    }

    public UUID getMineralId() { return mineralId; }
    public void setMineralId(UUID mineralId) { this.mineralId = mineralId; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getAttributeValue() { return attributeValue; }
    public void setAttributeValue(String attributeValue) { this.attributeValue = attributeValue; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotspotAlertFired that = (HotspotAlertFired) o;
        return Objects.equals(mineralId, that.mineralId) && Objects.equals(location, that.location) && Objects.equals(attributeValue, that.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mineralId, location, attributeValue);
    }
}