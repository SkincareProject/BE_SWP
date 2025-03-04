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
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;

    String answer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions questions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Answers(String answer, Questions questions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.answer = answer;
        this.questions = questions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
