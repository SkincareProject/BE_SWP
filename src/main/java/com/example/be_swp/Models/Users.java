package com.example.be_swp.Models;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username" )
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    private boolean is_active;
    private String token;

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

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<ExpertRatings> expertRatingsList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<ServiceRatings> serviceRatingsList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Blogs> blogsList;



    public Users(String username, String password, String fullName, String email, String phone, boolean is_active, LocalDateTime created_at, LocalDateTime updated_at ) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.is_active = is_active;
        this.created_at = created_at;
        this.updated_at = updated_at;

    }

    public Users(String token) {
        this.token = token;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
