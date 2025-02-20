package com.example.be_swp.DTOs;

import com.example.be_swp.Models.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {


    @NotBlank(message = "Username can not blank")
    private String username;
    @NotBlank(message = "Password can not blank")
    private String password;
    @NotBlank(message = "Fullname can not blank")
    private String fullName;
    @Email(message = "Invalid Email ")
    private String email;
    @NotBlank(message = "Phone can not blank")
    private String phone;

    private boolean is_active;

    private Integer role_id ;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}
