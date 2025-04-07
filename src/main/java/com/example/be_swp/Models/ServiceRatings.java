package com.example.be_swp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServiceRatings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceRatingId;

    private Long rating;

    private String feedback;

    @Column(name="customer_id")
    private Long userId;

    private Date createdAt=new Date();


    @Column(name="service_id")
    private Long serviceId;

    @Column(name="appointment_id")
    private Long appointmentId;




}
