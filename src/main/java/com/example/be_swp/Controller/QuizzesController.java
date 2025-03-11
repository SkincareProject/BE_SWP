package com.example.be_swp.Controller;


import com.example.be_swp.DTOs.Test.QuizDTO;
import com.example.be_swp.Models.Quizzes;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Service.QuizzesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/quizzes")
public class QuizzesController {

    @Autowired
    private  QuizzesService quizzesService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizDTO,  String username) {
        Users user = quizzesService.getUserByUsername(username);
        if (user.getRoles().getId() == 4 || user.getRoles().getId() == 1) { // Expert hoặc Admin
            Quizzes newQuiz = quizzesService.createQuiz(quizDTO);
            return ResponseEntity.ok(newQuiz);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission to create quiz");
    }



    @GetMapping("/findAll")
    public ResponseEntity<List<Quizzes>> getAllQuizzes() {
        List<Quizzes> quizzes = quizzesService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable int id) {
        Quizzes quiz = quizzesService.getQuizById(id);
        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuiz(@PathVariable int id, @RequestBody QuizDTO quizDTO,  String username) {
        Users user = quizzesService.getUserByUsername(username);
        if (user.getRoles().getId() == 4 || user.getRoles().getId() == 1) { // Expert hoặc Admin
            Quizzes updatedQuiz = quizzesService.updateQuiz(id, quizDTO);
            return ResponseEntity.ok(updatedQuiz);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission to update quiz");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id) {
            quizzesService.deleteQuiz(id);
            return ResponseEntity.ok("Quiz deleted successfully");
        }

    }

