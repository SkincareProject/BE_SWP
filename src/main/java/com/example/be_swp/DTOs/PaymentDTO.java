package com.example.be_swp.DTOs;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private int paymentId;

    private Long appointmentId;

    private int paymentMethod;

    private double price;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private long zp_trans_id;
}
