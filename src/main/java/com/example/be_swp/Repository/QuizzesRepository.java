package com.example.be_swp.Repository;

import com.example.be_swp.Models.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizzesRepository extends JpaRepository<Quizzes, Integer> {
    @Query("SELECT q FROM Quizzes q LEFT JOIN FETCH q.questionsList")
    List<Quizzes> findAllWithQuestions();

    @Query("SELECT q FROM Quizzes q LEFT JOIN FETCH q.questionsList WHERE q.quizId = :quizId")
    Optional<Quizzes> findQuizByIdWithQuestions(@Param("quizId") Integer quizId);
}
