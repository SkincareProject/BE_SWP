package com.example.be_swp.DTOs;

import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Models.Experts;
import com.example.be_swp.Models.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertRatingDTO {

    private Long expertRatingId;

    private Long appointmentId;

    private int customerId;

    private int expertId;

    private String expertName;

    private int rating;

    private String feedback;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}
