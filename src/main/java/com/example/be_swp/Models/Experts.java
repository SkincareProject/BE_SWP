package com.example.be_swp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Experts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int expertID;

    private int userID;

    private String specialization;

    private int yearOfExperiences;

    private String description;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}
