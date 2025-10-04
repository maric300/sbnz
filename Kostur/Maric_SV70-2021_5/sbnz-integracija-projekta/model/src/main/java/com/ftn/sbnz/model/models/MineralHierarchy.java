package com.ftn.sbnz.model.models;

import jakarta.persistence.*;
import org.kie.api.definition.type.Position;

import java.util.UUID;

@Entity
public class MineralHierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Position(0)
    @Column(nullable = false)
    private String subType;

    @Position(1)
    @Column(nullable = false)
    private String superType;

    // --- KONSTRUKTORI ---
    public MineralHierarchy() {}

    // --- RUČNO NAPISANI GETTERI I SETTERI ---

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSuperType() {
        return superType;
    }

    public void setSuperType(String superType) {
        this.superType = superType;
    }
}