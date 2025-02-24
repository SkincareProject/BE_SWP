package com.example.be_swp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExpertOccupiedTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expertOccupiedTimeId;

    private LocalDate date;

    private LocalTime startAt;

    private LocalTime endAt;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Experts experts;

}
