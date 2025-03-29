package com.example.be_swp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.Type;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expert_id")
    private Experts expertId;

    private Long serviceId;

    @JsonIgnore
    @OneToOne(mappedBy = "appointments", cascade = CascadeType.ALL)
    private ExpertRatings expertRatings;

    @JsonIgnore
    @OneToOne(mappedBy = "appointments", cascade = CascadeType.ALL)
    private ServiceRatings serviceRatings;

    @JsonIgnore
    @OneToOne(mappedBy = "appointments", cascade = CascadeType.ALL)
    private Payments payments;

    private double total;



    @Column(name = "booking_date")
    private LocalDate bookingDate;


    @Column(name = "start_at")
    private Long startAt;


    @Column(name = "end_at")
    private Long endAt;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}