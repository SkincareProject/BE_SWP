package com.example.be_swp.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quizzes quizzes;

    private String title;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<Answers> answersList;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;




}
