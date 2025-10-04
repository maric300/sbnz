package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Position;
import java.io.Serializable;

/**
 * Jednostavna klasa koja predstavlja jednu vezu u hijerarhiji.
 * Npr. "Ametist" (subType) je član "Kvarc" (superType).
 * Anotacije @Position su ključne za ispravan rad query-ja.
 */
public class MineralHierarchyFact implements Serializable {

    @Position(0)
    private String subType;

    @Position(1)
    private String superType;

    public MineralHierarchyFact(String subType, String superType) {
        this.subType = subType;
        this.superType = superType;
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