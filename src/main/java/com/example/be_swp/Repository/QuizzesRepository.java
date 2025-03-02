package com.example.be_swp.Repository;

import com.example.be_swp.Models.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzesRepository extends JpaRepository<Quizzes, Integer> {
}
