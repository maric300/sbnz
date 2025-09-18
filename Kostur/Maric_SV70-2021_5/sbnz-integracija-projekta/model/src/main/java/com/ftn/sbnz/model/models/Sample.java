package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.enums.Luster;
import com.ftn.sbnz.model.enums.Transparency;

public class Sample {
    private String location;
    private String color;
    private Transparency transparency;
    private Luster luster;
    private String rockType;

    public Sample() {

    }

    public Sample(String location, String color, Transparency transparency, Luster luster, String rockType) {
        this.location = location;
        this.color = color;
        this.transparency = transparency;
        this.luster = luster;
        this.rockType = rockType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Transparency getTransparency() {
        return transparency;
    }

    public void setTransparency(Transparency transparency) {
        this.transparency = transparency;
    }

    public Luster getLuster() {
        return luster;
    }

    public void setLuster(Luster luster) {
        this.luster = luster;
    }

    public String getRockType() {
        return rockType;
    }

    public void setRockType(String rockType) {
        this.rockType = rockType;
    }
}

