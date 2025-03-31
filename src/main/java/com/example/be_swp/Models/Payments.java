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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private Long appointmentId;

    private int status;

    private double price;

    private long zpTransId;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @Column(name="user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethods paymentMethods;

    public Payments(int status, double price, LocalDateTime created_at, LocalDateTime updated_at) {
        this.status = status;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
