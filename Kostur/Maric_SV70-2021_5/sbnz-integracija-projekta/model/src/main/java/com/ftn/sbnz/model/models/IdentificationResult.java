package com.ftn.sbnz.model.models;

public class IdentificationResult {
    private Mineral mineral;
    private int score;
    private String message;

    public IdentificationResult() {
        this.score = 0;
        this.message = "Base match.";
    }

    public IdentificationResult(Mineral mineral) {
        this.mineral = mineral;
        this.score = 0;
        this.message = "Base match.";
    }

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

    @Override
    public String toString() {
        return "IdentificationResult{" +
                "mineral=" + mineral.getName() +
                ", score=" + score +
                '}';
    }
}
