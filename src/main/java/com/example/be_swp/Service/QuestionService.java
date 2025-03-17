package com.example.be_swp.Service;

import com.example.be_swp.DTOs.QuestionDTO;
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
import java.time.ZoneId;
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
        question.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        question.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));

        List<Answers> answers = questionDTO.getAnswerDTO().stream().map(answerDTO -> {
            Answers answers1 = new Answers();
            answers1.setAnswer(answerDTO.getAnswer());
            answers1.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
            answers1.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
            answers1.setQuestions(question);
            return answers1;
        }).collect(Collectors.toList());
        question.setAnswersList(answers);
        return questionsRepository.save(question);
    }

    public List<QuestionDTO> findAllQuestions() {
        List<Questions> questionsList = questionsRepository.findAllWithAnswers();
        return questionsList.stream()
                .map(QuestionDTO::new) // Chuyển đổi sang DTO
                .collect(Collectors.toList());
    }

    public QuestionDTO findQuestionById(Integer id) {
        Questions question = questionsRepository.findByIdWithAnswers(id)
                .orElseThrow(() -> new UserNotFoundException( "Question not found"));

        return new QuestionDTO(question);
    }

    public Questions updateQuestion(int id , QuestionDTO questionDTO){
        Questions questions = questionsRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Question is not found"));
        questions.setTitle(questionDTO.getTitle());
        questions.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));

        if(questionDTO.getAnswerDTO() != null && !questionDTO.getAnswerDTO().isEmpty()){
            List<Answers> answers = questionDTO.getAnswerDTO().stream().map(answerDTO -> {
                Answers answers1 = new Answers();
                answers1.setAnswer(answerDTO.getAnswer());
                answers1.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
                answers1.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
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
