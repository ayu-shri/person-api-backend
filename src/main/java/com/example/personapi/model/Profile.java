package com.example.personapi.model.focusgroup;

public class Profile {
    private String name;
    private int age;
    private String occupation;
    private String trait;
    private String techLiteracy;
    private String financialStatus;
    private int index;

    public Profile() {}

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }

    public String getTrait() { return trait; }
    public void setTrait(String trait) { this.trait = trait; }

    public String getTechLiteracy() { return techLiteracy; }
    public void setTechLiteracy(String techLiteracy) { this.techLiteracy = techLiteracy; }

    public String getFinancialStatus() { return financialStatus; }
    public void setFinancialStatus(String financialStatus) { this.financialStatus = financialStatus; }

    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }
}