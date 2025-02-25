package com.example.be_swp.Repository;

import com.example.be_swp.Models.Quizzes;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzesRepository extends ListCrudRepository<Quizzes, Integer> {
}
