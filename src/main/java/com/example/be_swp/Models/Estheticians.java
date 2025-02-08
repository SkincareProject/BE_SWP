package com.example.be_swp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Estheticians {

    @Id
    private int estheticianID;

    private String fullName;

    private String specializeIn;

    private int yearOfExperiences;

    private String email;

    public Estheticians() {
    }

    public Estheticians(String fullName, String specializeIn, int yearOfExperiences, String email) {
        this.fullName = fullName;
        this.specializeIn = specializeIn;
        this.yearOfExperiences = yearOfExperiences;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getEstheticianID() {
        return estheticianID;
    }

    public void setEstheticianID(int estheticianID) {
        this.estheticianID = estheticianID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYearOfExperiences() {
        return yearOfExperiences;
    }

    public void setYearOfExperiences(int yearOfExperiences) {
        this.yearOfExperiences = yearOfExperiences;
    }

    public String getSpecializeIn() {
        return specializeIn;
    }

    public void setSpecializeIn(String specializeIn) {
        this.specializeIn = specializeIn;
    }
}
