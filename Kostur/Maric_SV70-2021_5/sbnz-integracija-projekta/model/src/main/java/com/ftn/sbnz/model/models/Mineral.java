package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.enums.Accessibility;
import com.ftn.sbnz.model.enums.Difficulty;
import com.ftn.sbnz.model.enums.Luster;
import com.ftn.sbnz.model.enums.Transparency;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mineral {
    private Long id;
    private String name;
    private List<String> colors;
    private Transparency transparency;
    private Luster luster;
    private double hardnessMin;
    private double hardnessMax;
    private String streakColor;
    private List<String> rockTypes;
    private List<String> locations;
    private Accessibility accessibility;
    private Difficulty difficulty;

    public Mineral() {
        this.colors = new ArrayList<>();
        this.rockTypes = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    public Mineral(Long id, String name, List<String> colors, Transparency transparency, Luster luster, double hardnessMin, double hardnessMax, String streakColor, List<String> rockTypes, List<String> locations, Accessibility accessibility, Difficulty difficulty) {
        this.id = id;
        this.name = name;
        this.colors = colors;
        this.transparency = transparency;
        this.luster = luster;
        this.hardnessMin = hardnessMin;
        this.hardnessMax = hardnessMax;
        this.streakColor = streakColor;
        this.rockTypes = rockTypes;
        this.locations = locations;
        this.accessibility = accessibility;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
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

    public double getHardnessMin() {
        return hardnessMin;
    }

    public void setHardnessMin(double hardnessMin) {
        this.hardnessMin = hardnessMin;
    }

    public double getHardnessMax() {
        return hardnessMax;
    }

    public void setHardnessMax(double hardnessMax) {
        this.hardnessMax = hardnessMax;
    }

    public String getStreakColor() {
        return streakColor;
    }

    public void setStreakColor(String streakColor) {
        this.streakColor = streakColor;
    }

    public List<String> getRockTypes() {
        return rockTypes;
    }

    public void setRockTypes(List<String> rockTypes) {
        this.rockTypes = rockTypes;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Accessibility getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Accessibility accessibility) {
        this.accessibility = accessibility;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Mineral mineral = (Mineral) object;
        return Objects.equals(id, mineral.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Mineral{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
