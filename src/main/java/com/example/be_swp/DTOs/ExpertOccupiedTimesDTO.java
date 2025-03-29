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
@NoArgsConstructor
@AllArgsConstructor

public class ExpertOccupiedTimesDTO {

    private int expertOccupiedTimeId;

    private Long expertId;

    private String expertName;

    private LocalDate date;

    private LocalTime startAt;

    private LocalTime endAt;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}
