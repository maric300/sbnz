package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class SeasonalTip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mineral_id", nullable = false)
    @JsonBackReference // Sprečava beskonačnu rekurziju pri serijalizaciji
    private Mineral mineral;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, length = 500)
    private String tipMessage;

    // Konstruktori, Getteri i Setteri
    public SeasonalTip() {}
    public SeasonalTip(Mineral mineral, String location, String tipMessage) {
        this.mineral = mineral;
        this.location = location;
        this.tipMessage = tipMessage;
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Mineral getMineral() { return mineral; }
    public void setMineral(Mineral mineral) { this.mineral = mineral; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getTipMessage() { return tipMessage; }
    public void setTipMessage(String tipMessage) { this.tipMessage = tipMessage; }
}
