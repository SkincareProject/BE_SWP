package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleID;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private String description;

}
