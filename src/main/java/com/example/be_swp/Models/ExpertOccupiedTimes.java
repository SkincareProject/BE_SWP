package com.example.be_swp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity


@Table(name = "expert_occupied_times")  // Đảm bảo đúng tên bảng
public class ExpertOccupiedTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expertOccupiedTimeId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_at")
    private Long startAt;

    @Column(name = "end_at")
    private Long endAt;

    @JsonIgnore
    @Nullable
    @Column(name = "appointment_id")
    private Long appointmentId;

    private int status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @JsonIgnore

    @Column(name = "expert_id")
    private Long expertId;

}