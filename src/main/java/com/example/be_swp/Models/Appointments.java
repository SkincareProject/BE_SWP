package com.example.be_swp.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;


public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingHistoryID;

    private LocalDateTime dateTime;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private double total;

}
