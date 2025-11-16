package com.example.personapi.model;

public class Person {
    private String name;
    private String email;
    private String phone;
    private String location;
    private String occupation;
    private String dateOfBirth;
    private String bio;

    public Person(String name, String email, String phone, String location, 
                  String occupation, String dateOfBirth, String bio) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.occupation = occupation;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}