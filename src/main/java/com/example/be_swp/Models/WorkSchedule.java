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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workScheduleId;

    private LocalTime start_at;

    private LocalTime end_at;

    private LocalDate work_date;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Experts experts;

    public WorkSchedule(LocalTime start_at, LocalTime end_at, LocalDate work_date, int status, LocalDateTime created_at, LocalDateTime updated_at) {
        this.start_at = start_at;
        this.end_at = end_at;
        this.work_date = work_date;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
