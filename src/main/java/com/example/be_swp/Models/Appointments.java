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

    @Column(nullable = false, updatable = false,name = "user_id")
    private Long userId;


    @Column(nullable = false, updatable = false,name = "expert_id")
    private Long expertId;

    private Long serviceId;

    @JsonIgnore
    @OneToOne(mappedBy = "appointments", cascade = CascadeType.ALL)
    private ExpertRatings expertRatings;

    @JsonIgnore
    @OneToOne(mappedBy = "appointments", cascade = CascadeType.ALL)
    private ServiceRatings serviceRatings;

//    @JsonIgnore
//    @OneToOne(mappedBy = "appointmentId", cascade = CascadeType.ALL)


    @Column(name = "payment_id", nullable = true)
    private Long paymentId;

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