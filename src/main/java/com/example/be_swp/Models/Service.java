package com.example.be_swp.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceID;

    private String serviceName;

    private double price;

    private String description;

    @OneToMany(mappedBy = "service")
    private List<BookingHistory> bookingHistories;

    public Service() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public Service(String serviceName, double price, String description) {
        this.serviceName = serviceName;
        this.price = price;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<BookingHistory> getBookingHistories() {
        return bookingHistories;
    }

    public void setBookingHistories(List<BookingHistory> bookingHistories) {
        this.bookingHistories = bookingHistories;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
}
