package com.example.be_swp.DTOs.Appointments;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;

    private int usersId;

    private int expertsId;

    private String expertsName;

    private int servicesId;

    private int paymentStatus;

    private double total;

    private Long start_at;

    private Long end_at;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}
