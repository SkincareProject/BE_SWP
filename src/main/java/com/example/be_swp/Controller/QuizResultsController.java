package com.example.be_swp.Controller;

import com.example.be_swp.Models.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizResults")
public class QuizResultsController {

    @GetMapping
    public ApiResponse<String> get(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @PostMapping
    public ApiResponse<String> post(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @PutMapping
    public ApiResponse<String> put(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @DeleteMapping
    public ApiResponse<String> delete(){

        return new ApiResponse<>("This is","Dummy","Api");
    }
}
