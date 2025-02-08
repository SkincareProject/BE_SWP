package com.example.be_swp.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customers {

    @Id
    private int customerID;

    private String fullName;

    private String email;


    public Customers() {
    }

    public Customers(int customerID, String fullName, String email) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
    }

    public Customers(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
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
}
