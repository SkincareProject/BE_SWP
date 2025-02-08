package com.example.demo.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Staffs_Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "staff_ID")
    private Staffs staffs;


    @ManyToOne
    @JoinColumn(name = "schedule_ID")
    private Schedules schedules;


    public Staffs_Schedules() {
    }

    public Staffs_Schedules(Staffs staffs, Schedules schedules) {
        this.staffs = staffs;
        this.schedules = schedules;
    }

    public Staffs getStaffs() {
        return staffs;
    }

    public void setStaffs(Staffs staffs) {
        this.staffs = staffs;
    }

    public Schedules getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedules schedules) {
        this.schedules = schedules;
    }
}
