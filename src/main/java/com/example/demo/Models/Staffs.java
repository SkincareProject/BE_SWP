package com.example.demo.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Staffs {

    @Id
    private int staffID;

    private String fullName;

    private String email;

    @OneToMany(mappedBy = "staffs", cascade = CascadeType.ALL)
    private List<Staffs_Schedules> schedulesList;


    public Staffs() {
    }

    public Staffs(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public List<Staffs_Schedules> getSchedulesList() {
        return schedulesList;
    }

    public void setSchedulesList(List<Staffs_Schedules> schedulesList) {
        this.schedulesList = schedulesList;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
