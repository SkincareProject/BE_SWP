package com.example.be_swp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Table(name="experts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields in JSON
public class Experts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expertId;

    private String specialization;

    private int yearOfExperiences;

    private String description;

    @Lob
    private String imageBase64;

    private int status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;




    @JsonIgnore
    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private List<WorkSchedule> workScheduleList;

    @JsonIgnore
    @OneToMany(mappedBy = "expertId", cascade = CascadeType.ALL)
    private List<Appointments> appointmentsList;

    @JsonIgnore
    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private List<ExpertRatings> expertRatingsList;

    @JsonIgnore
    @OneToMany(mappedBy = "expertId", cascade = CascadeType.ALL)
    private List<ExpertOccupiedTimes> expertOccupiedTimesList;

    @JsonIgnore
    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
    private List<Posts> postsList;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;



    public Experts(String specialization, int yearOfExperiences, String description, String imageBase64, int status, LocalDateTime created_at, LocalDateTime updated_at) {
        this.specialization = specialization;
        this.yearOfExperiences = yearOfExperiences;
        this.description = description;
        this.imageBase64 = imageBase64;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
