package com.ftn.sbnz.model.models;

import java.io.Serializable;

/**
 * Predstavlja korisnički unos (cilj) za rekurzivnu pretragu.
 */
public class RecursiveSearchQuery implements Serializable {
    private String mineralGroup;
    private String location;

    public RecursiveSearchQuery() { }

    public String getMineralGroup() {
        return mineralGroup;
    }

    public void setMineralGroup(String mineralGroup) {
        this.mineralGroup = mineralGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}