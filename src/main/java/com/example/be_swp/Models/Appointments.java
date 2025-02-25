package com.example.be_swp.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Experts experts;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services services;

    @OneToOne(mappedBy = "appointments",cascade = CascadeType.ALL)
    private ExpertRatings expertRatings;

    @OneToOne(mappedBy = "appointments",cascade = CascadeType.ALL)
    private ServiceRatings serviceRatings;

    @OneToOne(mappedBy = "appointments", cascade = CascadeType.ALL)
    private Payments payments;

    private double total;

    private LocalDateTime start_at;

    private LocalDateTime end_at;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
