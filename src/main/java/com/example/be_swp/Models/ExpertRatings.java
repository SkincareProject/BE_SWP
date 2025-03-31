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

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Experts experts;

    private Long rating;

    private String feedback;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public ExpertRatings(Appointments appointments, Experts experts, Users users, Long rating, String feedback, int status, LocalDateTime created_at, LocalDateTime updated_at) {
        this.appointments = appointments;
        this.experts = experts;
        this.users = users;
        this.rating = rating;
        this.feedback = feedback;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
