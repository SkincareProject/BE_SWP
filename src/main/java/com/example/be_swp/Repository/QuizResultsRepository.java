package com.example.be_swp.Repository;

import com.example.be_swp.Models.QuizResults;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultsRepository extends ListCrudRepository<QuizResults, Integer> {
}
