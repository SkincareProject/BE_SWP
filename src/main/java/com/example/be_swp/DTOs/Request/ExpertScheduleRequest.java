package com.example.be_swp.DTOs.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpertScheduleRequest {
    private Long expertId;
    private LocalDate date;
}
