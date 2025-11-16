package com.example.personapi.model.focusgroup;

public class ProfileReaction {
    private int profileIndex;
    private String name;
    private String sentiment;
    private String reason;

    public ProfileReaction() {}

    public int getProfileIndex() { return profileIndex; }
    public void setProfileIndex(int profileIndex) { this.profileIndex = profileIndex; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}