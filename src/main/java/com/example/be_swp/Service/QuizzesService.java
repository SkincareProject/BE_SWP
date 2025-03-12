package com.example.be_swp.Service;



import com.example.be_swp.DTOs.QuizDTO;
import com.example.be_swp.Exceptions.UserNotFoundException;
import com.example.be_swp.Models.Answers;
import com.example.be_swp.Models.Questions;
import com.example.be_swp.Models.Quizzes;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.QuizzesRepository;
import com.example.be_swp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        quiz.setUpdateAt(LocalDateTime.now());


        List<Questions> questions = quizDTO.getQuestionDTO().stream().map(questionDTO -> {
            Questions questions1 = new Questions();
            questions1.setTitle(questionDTO.getTitle());
            questions1.setCreatedAt(LocalDateTime.now());
            questions1.setUpdatedAt(LocalDateTime.now());
            questions1.setQuizzes(quiz);

            List<Answers> answers = questionDTO.getAnswerDTO().stream().map(answerDTO -> {
                Answers answers1 = new Answers();
                answers1.setAnswer(answerDTO.getAnswer());
                answers1.setCreatedAt(LocalDateTime.now());
                answers1.setUpdatedAt(LocalDateTime.now());
                answers1.setQuestions(questions1);

                return answers1;
            }).collect(Collectors.toList());

            questions1.setAnswersList(answers);
            return questions1;


        }).collect(Collectors.toList());
        quiz.setQuestionsList(questions);
        return quizzesRepository.save(quiz);
    }

//    public List<Quizzes> getAllQuizzes() {
//        return quizzesRepository.findAll();
//    }

//    public List<QuizDTO> getAllQuizzes() {
//        List<Quizzes> quizzesList = quizzesRepository.findAll();
//        List<QuizDTO> quizDTOList = new ArrayList<>();
//        if(!quizzesList.isEmpty()){
//            for(Quizzes quizzes : quizzesList){
//                QuizDTO quizDTO = new QuizDTO();
//                quizDTO.setQuizId(quizzes.getQuizId());
//                quizDTO.setName(quizzes.getName());
//
//                quizDTO.setCreateAt(quizzes.getCreateAt());
//
//                quizDTOList.add(quizDTO);
//            }
//        }
//        return quizDTOList;
//    }

    public List<Quizzes> getAllQuizzes() {
        List<Quizzes> quizzesList = quizzesRepository.findAll();
        if(!quizzesList.isEmpty()){
            Quizzes quizzes1 = new Quizzes();
            quizzesList.add(quizzes1);
        }
        return quizzesList;
    }

    public Quizzes getQuizById(int id) {
        return quizzesRepository.findById(id).orElse(null);
    }

    public Quizzes updateQuiz(int id, QuizDTO quizDTO) {
        Quizzes quiz = quizzesRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Quiz not found"));
        quiz.setName(quizDTO.getName());
        quiz.setUpdateAt(LocalDateTime.now());

        if(quizDTO.getQuestionDTO() != null && !quizDTO.getQuestionDTO().isEmpty()){
            List<Questions> questions = quizDTO.getQuestionDTO().stream().map(questionDTO -> {
                Questions questions1 = new Questions();
                questions1.setTitle(questionDTO.getTitle());
                questions1.setCreatedAt(LocalDateTime.now());
                questions1.setUpdatedAt(LocalDateTime.now());
                questions1.setQuizzes(quiz);

                List<Answers> answers = questionDTO.getAnswerDTO().stream().map(answerDTO -> {
                    Answers answers1 = new Answers();
                    answers1.setAnswer(answerDTO.getAnswer());
                    answers1.setCreatedAt(LocalDateTime.now());
                    answers1.setUpdatedAt(LocalDateTime.now());
                    answers1.setQuestions(questions1);

                    return answers1;
                }).collect(Collectors.toList());

                questions1.setAnswersList(answers);
                return questions1;


            }).collect(Collectors.toList());
            quiz.setQuestionsList(questions);

        }
        return quizzesRepository.save(quiz);
    }

    public void deleteQuiz(int id) {
        Quizzes quiz = quizzesRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Quiz not found"));
        quizzesRepository.delete(quiz);
    }


}
