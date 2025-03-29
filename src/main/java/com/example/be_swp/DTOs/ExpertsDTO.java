package com.example.be_swp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpertsDTO {



    private Long expertId;

    private int userId;

    private String fullName;

    private String specialization;

    private int yearOfExperiences;

    private String description;

    private int status;



    private String imageBase64;

}
