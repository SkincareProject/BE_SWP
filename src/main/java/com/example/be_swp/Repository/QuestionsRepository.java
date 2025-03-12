package com.example.be_swp.Repository;

import com.example.be_swp.Models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
    @Query("SELECT q FROM Questions q LEFT JOIN FETCH q.answersList WHERE q.questionId = :questionId")
    Optional<Questions> findByIdWithAnswers(@Param("questionId") Integer questionId);

    @Query("SELECT q FROM Questions q LEFT JOIN FETCH q.answersList")
    List<Questions> findAllWithAnswers();


}
