package com.example.be_swp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private int quizId;
    private String name;
    private LocalDateTime createAt;
    private List<QuestionDTO> questionDTO;
}
