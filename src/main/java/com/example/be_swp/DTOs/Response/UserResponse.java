package com.example.be_swp.DTOs.Response;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UserResponse {

    public int id;
    @NotBlank(message = "Username can not blank")
    public String username;
    @NotBlank(message = "Password can not blank")
    public String password;
    @NotBlank(message = "Fullname can not blank")
    public String fullName;
    @Email(message = "Invalid Email ")
    public String email;
    @NotBlank(message = "Phone can not blank")
    public String phone;

    public boolean is_active;

    public Integer role_id;

    public LocalDateTime created_at;

    public LocalDateTime updated_at;

    public String token;

    public UserResponse(int id, String username, String password, String fullName, String email, String phone, boolean is_active, Integer role_id, LocalDateTime created_at, LocalDateTime updated_at, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.is_active = is_active;
        this.role_id = role_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.token = token;
    }

    public UserResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
