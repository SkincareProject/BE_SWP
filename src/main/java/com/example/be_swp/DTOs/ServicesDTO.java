package com.example.be_swp.DTOs;

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
public class ServicesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;

    private String serviceName;

    private double price;

    private String description;

    private int duration;

    private int status;

    private String type;

    private String skinType;

    private String serviceImage;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
