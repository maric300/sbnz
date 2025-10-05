package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class PotentialCorrectionEvent implements Serializable {

    private Date timestamp;
    private UUID mineralId;
    private String mineralName;
    private String location;
    private String attributeName = "rockType";
    private String databaseValue;
    private String reportedValue;

    // ISPRAVKA: Tip je sada UUID
    private UUID userId;

    public PotentialCorrectionEvent() {
        this.timestamp = new Date();
    }

    // ISPRAVKA: Konstruktor sada prihvata UUID
    public PotentialCorrectionEvent(UUID mineralId, String mineralName, String location, String databaseValue, String reportedValue, UUID userId) {
        this.timestamp = new Date();
        this.mineralId = mineralId;
        this.mineralName = mineralName;
        this.location = location;
        this.databaseValue = databaseValue;
        this.reportedValue = reportedValue;
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
    public String getAttributeName() { return attributeName; }
    public void setAttributeName(String attributeName) { this.attributeName = attributeName; }
    public String getDatabaseValue() { return databaseValue; }
    public void setDatabaseValue(String databaseValue) { this.databaseValue = databaseValue; }
    public String getReportedValue() { return reportedValue; }
    public void setReportedValue(String reportedValue) { this.reportedValue = reportedValue; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PotentialCorrectionEvent that = (PotentialCorrectionEvent) o;
        return Objects.equals(mineralId, that.mineralId) && Objects.equals(location, that.location) && Objects.equals(reportedValue, that.reportedValue) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mineralId, location, reportedValue, userId);
    }
}