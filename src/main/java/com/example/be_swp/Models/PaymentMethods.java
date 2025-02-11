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
public class PaymentMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentMethodId;

    private String name;

    private String description;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "paymentMethods", cascade = CascadeType.ALL)
    private List<Payments> payments;

}
