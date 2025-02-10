package com.example.be_swp.Models;

import jakarta.persistence.*;

import java.util.List;

//@Entity
public class Staffs {

    @Id
    private int staffID;

    private String fullName;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "Staff_Schedule",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private List<Schedules> schedules;

    public Staffs() {
    }

    public Staffs(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
