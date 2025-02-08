package com.example.demo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleID;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private String description;

    @OneToMany(mappedBy = "schedules", cascade = CascadeType.ALL)
    private List<Staffs_Schedules> staffsSchedulesList;

    public Schedules() {
    }

    public Schedules(String description, LocalDateTime timeEnd, LocalDateTime timeStart) {
        this.description = description;
        this.timeEnd = timeEnd;
        this.timeStart = timeStart;
    }

    public List<Staffs_Schedules> getStaffsSchedulesList() {
        return staffsSchedulesList;
    }

    public void setStaffsSchedulesList(List<Staffs_Schedules> staffsSchedulesList) {
        this.staffsSchedulesList = staffsSchedulesList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }
}
