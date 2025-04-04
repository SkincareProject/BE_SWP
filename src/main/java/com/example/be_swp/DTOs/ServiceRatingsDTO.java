package com.example.be_swp.DTOs;

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
public class ServiceRatingsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceRatingId;

    private int appointmentsId;

    private int usersId;

    private String customerName;

    private int servicesId;

    private String serviceName;

    private int rating;

    private String feedback;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
