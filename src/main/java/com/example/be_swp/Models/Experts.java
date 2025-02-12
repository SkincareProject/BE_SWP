package com.example.be_swp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Experts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int expertId;

    private String specialization;

    private int yearOfExperiences;

    private String description;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private List<WorkSchedule> workScheduleList;

    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private List<Appointments> appointmentsList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;

}
