package com.example.be_swp.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpertsDTO {



    private Long expertId;

    private Long userId;

    private String fullName;

    private String specialization;

    private int yearOfExperiences;

    private String description;

    private int status;

    private String customerName;

    private String serviceName;

    private Long startAt;
    private Long endAt;
    private LocalDate date;
    private int duration;
    private Double total;
    private Long appointmentId;

    private String imageBase64;

}
