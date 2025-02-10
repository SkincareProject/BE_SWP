package com.example.be_swp.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleID;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private String description;

    @ManyToMany(mappedBy = "schedules")
    private List<Staffs> staffs;

    public Schedules() {
    }

    public Schedules(String description, LocalDateTime timeEnd, LocalDateTime timeStart) {
        this.description = description;
        this.timeEnd = timeEnd;
        this.timeStart = timeStart;
    }
}
