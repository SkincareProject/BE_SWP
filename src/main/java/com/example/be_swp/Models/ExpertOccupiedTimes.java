package com.example.be_swp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name="date")
    private LocalDate date;

    @Column(name = "start_at")  // Kiểm tra đúng tên cột
    private int startAt;

    @Column(name = "end_at")   // Kiểm tra đúng tên cột
    private int endAt;

    private int status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long expertId;
}
