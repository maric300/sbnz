package com.ftn.sbnz.model.models;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    private List<Mineral> recommendations = new ArrayList<>();
    private String followUpQuestion;
    private List<String> possibleAnswers;

    public List<Mineral> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Mineral> recommendations) {
        this.recommendations = recommendations;
    }

    public String getFollowUpQuestion() {
        return followUpQuestion;
    }

    public void setFollowUpQuestion(String followUpQuestion) {
        this.followUpQuestion = followUpQuestion;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}