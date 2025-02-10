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
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int workScheduleID;

    private int expertID;

    private LocalTime start_at;

    private LocalTime end_at;

    private LocalDate work_date;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Experts experts;

}
