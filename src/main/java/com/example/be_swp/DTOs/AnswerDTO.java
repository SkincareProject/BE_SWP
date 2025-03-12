package com.example.be_swp.DTOs;

import com.example.be_swp.Models.Answers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private String answer;

    public AnswerDTO(Answers answer) {
        this.answer = answer.getAnswer(); // Lấy giá trị từ entity Answers
    }
}

