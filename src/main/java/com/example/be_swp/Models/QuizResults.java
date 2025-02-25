package com.example.be_swp.Models;

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
@Entity
public class QuizResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizResultId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quizzes quizzes;

    private String finalResult;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
