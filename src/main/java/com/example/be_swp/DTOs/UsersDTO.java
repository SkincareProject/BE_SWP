package com.example.be_swp.DTOs;

import com.example.be_swp.Models.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {



    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;

    private boolean is_active;

    private Integer role_id ;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
