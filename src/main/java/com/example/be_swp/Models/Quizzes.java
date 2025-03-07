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
public class Quizzes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;

    private String name;


    @OneToMany(mappedBy = "quizzes", cascade = CascadeType.ALL)
    List<Questions> questionsList;

    @OneToMany(mappedBy = "quizzes", cascade = CascadeType.ALL)
    List<QuizResults> quizResultsList;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
