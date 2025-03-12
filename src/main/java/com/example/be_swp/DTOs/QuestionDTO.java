package com.example.be_swp.DTOs;

import com.example.be_swp.Models.Answers;
import com.example.be_swp.Models.Questions;
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
public class QuestionDTO {
    private int questionId;
    private String title;
    private LocalDateTime createAt;
    private List<AnswerDTO> answerDTO;

    private AnswerDTO convertToDTO(Answers answer) {
        AnswerDTO dto = new AnswerDTO();
        dto.setAnswer(answer.getAnswer());
        return dto;
    }

   public QuestionDTO(Questions question){
       this.questionId = question.getQuestionId();
       this.title = question.getTitle();
       this.createAt = question.getCreatedAt();
       this.answerDTO = (question.getAnswersList() != null) ?
               question.getAnswersList().stream().map(AnswerDTO::new).collect(Collectors.toList()) :
               new ArrayList<>();
   }
}
