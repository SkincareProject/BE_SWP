package com.example.be_swp.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;

    private int status;

    private double price;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethods paymentMethods;

}
