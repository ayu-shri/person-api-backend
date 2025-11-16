package com.example.personapi.model.focusgroup;

import java.util.List;

public class SimulationResult {
    private double overallSentimentScore;
    private String executiveSummary;
    private List<String> keyFindings;
    private List<ProfileReaction> profileReactions;

    public SimulationResult() {}

    public double getOverallSentimentScore() { return overallSentimentScore; }
    public void setOverallSentimentScore(double overallSentimentScore) { 
        this.overallSentimentScore = overallSentimentScore; 
    }

    public String getExecutiveSummary() { return executiveSummary; }
    public void setExecutiveSummary(String executiveSummary) { 
        this.executiveSummary = executiveSummary; 
    }

    public List<String> getKeyFindings() { return keyFindings; }
    public void setKeyFindings(List<String> keyFindings) { 
        this.keyFindings = keyFindings; 
    }

    public List<ProfileReaction> getProfileReactions() { return profileReactions; }
    public void setProfileReactions(List<ProfileReaction> profileReactions) { 
        this.profileReactions = profileReactions; 
    }
}