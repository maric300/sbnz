package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.enums.Luster;
import com.ftn.sbnz.model.enums.Transparency;
import java.util.UUID; // Importujemo UUID

public class Sample {
    private String location;
    private String color;
    private Transparency transparency;
    private Luster luster;
    private String rockType;

    // Polje koje će se popuniti na backendu
    private UUID userId;

    // Getteri i Setteri
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Transparency getTransparency() { return transparency; }
    public void setTransparency(Transparency transparency) { this.transparency = transparency; }
    public Luster getLuster() { return luster; }
    public void setLuster(Luster luster) { this.luster = luster; }
    public String getRockType() { return rockType; }
    public void setRockType(String rockType) { this.rockType = rockType; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
}