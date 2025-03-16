package com.example.be_swp.DTOs;

import com.example.be_swp.Models.Experts;
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
@AllArgsConstructor
@NoArgsConstructor
public class WorkScheduleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int workScheduleId;

    private int expertId;

    private String expertName;

    private LocalTime start_at;

    private LocalTime end_at;

    private LocalDate work_date;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}
