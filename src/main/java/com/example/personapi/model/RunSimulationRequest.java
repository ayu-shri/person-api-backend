package com.example.personapi.model.focusgroup;

import java.util.List;

public class RunSimulationRequest {
    private List<Profile> profiles;
    private String simulationPrompt;

    public RunSimulationRequest() {}

    public List<Profile> getProfiles() { return profiles; }
    public void setProfiles(List<Profile> profiles) { this.profiles = profiles; }

    public String getSimulationPrompt() { return simulationPrompt; }
    public void setSimulationPrompt(String simulationPrompt) { 
        this.simulationPrompt = simulationPrompt; 
    }
}