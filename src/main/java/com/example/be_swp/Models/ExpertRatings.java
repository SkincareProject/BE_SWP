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
public class ExpertRatings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expertRatingId;

    @Column(name="appointment_id")
    private Long appointmentId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="expert_id")
    private Long expertId;

    private Long rating;

    private String feedback;

    private int status;




}
