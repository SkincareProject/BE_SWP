package com.example.be_swp.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Customers {

    @Id
    private int customerID;

    private String fullName;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Appointments> bookingHistories;

    public Customers() {
    }

    public Customers(int customerID, String fullName, String email, String phone) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public List<Appointments> getBookingHistories() {
        return bookingHistories;
    }

    public void setBookingHistories(List<Appointments> bookingHistories) {
        this.bookingHistories = bookingHistories;
    }
}
