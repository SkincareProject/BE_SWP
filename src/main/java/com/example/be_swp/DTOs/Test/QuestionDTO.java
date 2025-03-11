package com.example.be_swp.DTOs.Test;

import com.example.be_swp.Models.Answers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private int questionId;
    private String title;
    private LocalDateTime createAt;
    private List<AnswerDTO> answerDTO;
}
