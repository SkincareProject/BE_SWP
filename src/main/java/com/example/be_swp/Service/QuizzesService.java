package com.example.be_swp.Service;



import com.example.be_swp.DTOs.Test.QuizDTO;
import com.example.be_swp.Exceptions.UserNotFoundException;
import com.example.be_swp.Models.Quizzes;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.QuizzesRepository;
import com.example.be_swp.Repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuizzesService {
    @Autowired
    private QuizzesRepository  quizzesRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Users getUserByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Quizzes createQuiz(QuizDTO quizDTO) {
        Quizzes quiz = new Quizzes();
        quiz.setName(quizDTO.getName());
        quiz.setCreateAt(LocalDateTime.now());
        if(quizDTO.getQuestions() != null){
            quiz.setQuestionsList(quizDTO.getQuestions());
        }
        return quizzesRepository.save(quiz);
    }

    public List<Quizzes> getAllQuizzes() {
        return quizzesRepository.findAll();
    }

    public Quizzes getQuizById(int id) {
        return quizzesRepository.findById(id).orElse(null);
    }

    public Quizzes updateQuiz(int id, QuizDTO quizDTO) {
        Quizzes quiz = quizzesRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Quiz not found"));
        quiz.setName(quizDTO.getName());
        quiz.setUpdateAt(LocalDateTime.now());
        if(quizDTO.getQuestions() != null){
            quiz.setQuestionsList(quizDTO.getQuestions());
        }
        return quizzesRepository.save(quiz);
    }

    public void deleteQuiz(int id) {
        Quizzes quiz = quizzesRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Quiz not found"));
        quizzesRepository.delete(quiz);
    }

    public QuizDTO convertToDTO(Quizzes quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setQuizId(quiz.getQuizId());
        dto.setName(quiz.getName());
        dto.setCreateAt(quiz.getCreateAt());
        dto.setQuestions(quiz.getQuestionsList());
        return dto;
    }
}
