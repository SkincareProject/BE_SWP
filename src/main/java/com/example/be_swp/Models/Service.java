package com.example.be_swp.Models;

import jakarta.persistence.*;

import java.util.List;

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceID;

    private String serviceName;

    private double price;

    private String description;

    @OneToMany(mappedBy = "service")
    private List<Appointments> bookingHistories;

    public Service() {
    }

}
