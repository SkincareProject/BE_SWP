package com.example.be_swp.DTOs.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;

public class UserRequest {
    public String username;
    public String password;

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
