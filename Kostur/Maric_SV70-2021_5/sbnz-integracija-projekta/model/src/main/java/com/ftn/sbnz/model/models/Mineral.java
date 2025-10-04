package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.enums.Accessibility;
import com.ftn.sbnz.model.enums.Difficulty;
import com.ftn.sbnz.model.enums.Luster;
import com.ftn.sbnz.model.enums.Transparency;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Mineral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> colors = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Transparency transparency;

    @Enumerated(EnumType.STRING)
    private Luster luster;

    private double hardnessMin;
    private double hardnessMax;
    private String streakColor;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> rockTypes = new ArrayList<>();

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> locations = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Accessibility accessibility;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    // --- KONSTRUKTORI ---
    public Mineral() {}

    // --- RUČNO NAPISANI GETTERI I SETTERI ---

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mineral mineral = (Mineral) o;
        return Objects.equals(id, mineral.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}