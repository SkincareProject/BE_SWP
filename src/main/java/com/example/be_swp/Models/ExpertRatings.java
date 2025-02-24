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
    private int expertRatingId;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Experts experts;

    private int rating;

    private String feedback;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}
