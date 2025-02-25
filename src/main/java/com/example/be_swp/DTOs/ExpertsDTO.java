package com.example.be_swp.DTOs;

import com.example.be_swp.Models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpertsDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int expertId;

    private int userId;

    private String specialization;

    private int yearOfExperiences;

    private String description;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private String imageBase64;

}
