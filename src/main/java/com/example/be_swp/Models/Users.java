package com.example.be_swp.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    private boolean is_active;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private Roles roles;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Experts experts;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Appointments> appointmentsList;

    public Users(String username, String password, String fullName, String email, String phone, boolean is_active, LocalDateTime created_at, LocalDateTime updated_at) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.is_active = is_active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
