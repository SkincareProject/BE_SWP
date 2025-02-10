package com.example.be_swp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentID;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Experts experts;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    private double total;

    private LocalDateTime start_at;

    private LocalDateTime end_at;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
