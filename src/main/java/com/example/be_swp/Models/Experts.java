package com.example.be_swp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class Experts {

    @Id
    private int estheticianID;

    private String fullName;

    private String specializeIn;

    private int yearOfExperiences;

    private String email;

    public Experts() {
    }

}
