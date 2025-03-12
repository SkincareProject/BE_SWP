package com.example.be_swp.DTOs;

import com.example.be_swp.Models.Quizzes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private int quizId;
    private String name;
    private LocalDateTime createAt;
    private List<QuestionDTO> questionDTO;

    public QuizDTO(Quizzes quiz){
        this.quizId = quiz.getQuizId();
        this.name = quiz.getName();
        this.createAt = quiz.getCreateAt();
        this.questionDTO = (quiz.getQuestionsList() != null) ?
                quiz.getQuestionsList().stream().map(QuestionDTO::new).collect(Collectors.toList()) :
                new ArrayList<>();
    }
}
