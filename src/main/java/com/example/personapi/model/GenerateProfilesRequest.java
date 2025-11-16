package com.example.personapi.model.focusgroup;

public class GenerateProfilesRequest {
    private String groupDescription;
    private int numProfiles;

    public GenerateProfilesRequest() {}

    public String getGroupDescription() { return groupDescription; }
    public void setGroupDescription(String groupDescription) { 
        this.groupDescription = groupDescription; 
    }

    public int getNumProfiles() { return numProfiles; }
    public void setNumProfiles(int numProfiles) { this.numProfiles = numProfiles; }
}