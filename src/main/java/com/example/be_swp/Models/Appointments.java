package com.example.be_swp.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingHistoryID;

    private LocalDateTime dateTime;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private double total;

    @ManyToOne
    @JoinColumn(name = "service_ID")
    private Service service;

    public Appointments() {
    }

    public Appointments(LocalDateTime checkOut, LocalDateTime dateTime, LocalDateTime checkIn, double total) {
        this.checkOut = checkOut;
        this.dateTime = dateTime;
        this.checkIn = checkIn;
        this.total = total;
    }
}
