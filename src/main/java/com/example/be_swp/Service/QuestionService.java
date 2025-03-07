package com.example.be_swp.Service;

import com.example.be_swp.Models.Questions;
import com.example.be_swp.Repository.QuestionsRepository;
import com.example.be_swp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Questions createQuestion(Questions questions){
        Questions question = new Questions();




        return questionsRepository.save(question);
    }


}
