package com.example.be_swp.Controller;


import com.example.be_swp.DTOs.Test.QuizDTO;
import com.example.be_swp.Models.Quizzes;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Service.QuizzesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
public class QuizzesController {

    @Autowired
    private  QuizzesService quizzesService;

    @PostMapping
    public ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizDTO,  String username) {
        Users user = quizzesService.getUserByUsername(username);
        if (user.getRoles().getId() == 4 || user.getRoles().getId() == 1) { // Expert hoặc Admin
            Quizzes newQuiz = quizzesService.createQuiz(quizDTO);
            QuizDTO responseDTO = quizzesService.convertToDTO(newQuiz);
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission to create quiz");
    }



    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        List<Quizzes> quizzes = quizzesService.getAllQuizzes();
        List<QuizDTO> quizDTOs = quizzes.stream().map(quizzesService::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(quizDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable int id) {
        Quizzes quiz = quizzesService.getQuizById(id);
        if (quiz != null) {
            QuizDTO quizDTO = quizzesService.convertToDTO(quiz);
            return ResponseEntity.ok(quizDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@PathVariable int id, @RequestBody QuizDTO quizDTO,  String username) {
        Users user = quizzesService.getUserByUsername(username);
        if (user.getRoles().getId() == 4 || user.getRoles().getId() == 1) { // Expert hoặc Admin
            Quizzes updatedQuiz = quizzesService.updateQuiz(id, quizDTO);
            QuizDTO responseDTO = quizzesService.convertToDTO(updatedQuiz);
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission to update quiz");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id) {
            quizzesService.deleteQuiz(id);
            return ResponseEntity.ok("Quiz deleted successfully");
        }

    }

