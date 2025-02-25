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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private List<ExpertRatings> expertRatingsList;

    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private List<ExpertOccupiedTimes> expertOccupiedTimesList;

    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private List<Posts> postsList;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;


    public Experts(String specialization, int yearOfExperiences, String description, int status, LocalDateTime created_at, LocalDateTime updated_at) {
        this.specialization = specialization;
        this.yearOfExperiences = yearOfExperiences;
        this.description = description;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
