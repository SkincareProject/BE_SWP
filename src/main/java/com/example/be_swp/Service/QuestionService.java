package com.example.be_swp.Service;

import com.example.be_swp.DTOs.Test.QuestionDTO;
import com.example.be_swp.Exceptions.UserNotFoundException;
import com.example.be_swp.Models.Answers;
import com.example.be_swp.Models.Questions;
import com.example.be_swp.Models.Quizzes;
import com.example.be_swp.Repository.QuestionsRepository;
import com.example.be_swp.Repository.QuizzesRepository;
import com.example.be_swp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuizzesRepository quizzesRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Questions createQuestion(QuestionDTO questionDTO, int quizId){
        Optional<Quizzes> quizzes =  quizzesRepository.findById(quizId);
        Quizzes quizzes1 = quizzes.get();
        Questions question = new Questions();
        question.setQuizzes(quizzes1);
        question.setTitle(questionDTO.getTitle());
        question.setCreatedAt(LocalDateTime.now());
        question.setUpdatedAt(LocalDateTime.now());

        List<Answers> answers = questionDTO.getAnswerDTO().stream().map(answerDTO -> {
            Answers answers1 = new Answers();
            answers1.setAnswer(answerDTO.getAnswer());
            answers1.setCreatedAt(LocalDateTime.now());
            answers1.setUpdatedAt(LocalDateTime.now());
            answers1.setQuestions(question);
            return answers1;
        }).collect(Collectors.toList());
        question.setAnswersList(answers);
        return questionsRepository.save(question);
    }

    public List<Questions> getAllQuestions(){
        return questionsRepository.findAll();
    }

    public Questions getQuestionById(int id){
        return questionsRepository.findById(id).orElse(null);
    }

    public Questions updateQuestion(int id , QuestionDTO questionDTO){
        Questions questions = questionsRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Question is not found"));
        questions.setTitle(questionDTO.getTitle());
        questions.setUpdatedAt(LocalDateTime.now());

        if(questionDTO.getAnswerDTO() != null && !questionDTO.getAnswerDTO().isEmpty()){
            List<Answers> answers = questionDTO.getAnswerDTO().stream().map(answerDTO -> {
                Answers answers1 = new Answers();
                answers1.setAnswer(answerDTO.getAnswer());
                answers1.setCreatedAt(LocalDateTime.now());
                answers1.setUpdatedAt(LocalDateTime.now());
                answers1.setQuestions(questions);
                return answers1;
            }).collect(Collectors.toList());
            questions.setAnswersList(answers);
        }
        return questionsRepository.save(questions);
    }

    public void deleteQuestion(int id){
        Questions questions = questionsRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Question is not found"));
        questionsRepository.delete(questions);
    }

    public QuestionDTO conrvertToDTO(Questions questions){
        QuestionDTO dto = new QuestionDTO();
        dto.setQuestionId(questions.getQuestionId());
        dto.setTitle(questions.getTitle());
        dto.setCreateAt(questions.getCreatedAt());
//        dto.setAnswersList(questions.getAnswersList());
        return dto;
    }
}
