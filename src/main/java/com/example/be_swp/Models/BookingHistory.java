package com.example.be_swp.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BookingHistory {

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

    @ManyToOne
    @JoinColumn(name = "customer_ID")
    private Customers customer;

    public BookingHistory() {
    }

    public BookingHistory(LocalDateTime checkOut, LocalDateTime dateTime, LocalDateTime checkIn, double total) {
        this.checkOut = checkOut;
        this.dateTime = dateTime;
        this.checkIn = checkIn;
        this.total = total;
    }

    public int getBookingHistoryID() {
        return bookingHistoryID;
    }

    public void setBookingHistoryID(int bookingHistoryID) {
        this.bookingHistoryID = bookingHistoryID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
