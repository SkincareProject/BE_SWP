package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.QuestionDTO;
import com.example.be_swp.Models.Questions;
import com.example.be_swp.Service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/create/{quizId}")
    @Transactional
    public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO questionDTO, @PathVariable int quizId) {
        Questions newQuestion = questionService.createQuestion(questionDTO, quizId);
        return ResponseEntity.ok(newQuestion);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> findAllQuestions() {
        List<QuestionDTO> questions = questionService.findAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> findQuestionById(@PathVariable Integer id) {
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        return ResponseEntity.ok(questionDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDTO questionDTO, @PathVariable int id) {
        Questions updateQuestion = questionService.updateQuestion(id, questionDTO);
        return ResponseEntity.ok(updateQuestion);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable int id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully");
    }
}
