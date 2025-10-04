package com.ftn.sbnz.model.models;

public class IdentificationResult {
    private Mineral mineral;
    private int score;
    private String message;
    private boolean isPrimaryCandidate;
    private double matchPercentage;

    public IdentificationResult() {
        this.score = 0;
        this.isPrimaryCandidate = false;
        this.matchPercentage = 0.0;
        this.message = "Početno stanje.";
    }

    public IdentificationResult(Mineral mineral) {
        this();
        this.mineral = mineral;
    }

    // ... (ostali getteri i setteri ostaju isti) ...

    public Mineral getMineral() {
        return mineral;
    }

    public void setMineral(Mineral mineral) {
        this.mineral = mineral;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPrimaryCandidate() {
        return isPrimaryCandidate;
    }

    public void setPrimaryCandidate(boolean primaryCandidate) {
        isPrimaryCandidate = primaryCandidate;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }


    @Override
    public String toString() {
        // ISPRAVKA: Proveravamo da li je 'mineral' null pre poziva getName()
        String mineralName = (this.mineral != null) ? this.mineral.getName() : "N/A";
        return "IdentificationResult{" +
                "mineral=" + mineralName +
                ", score=" + score +
                '}';
    }
}

