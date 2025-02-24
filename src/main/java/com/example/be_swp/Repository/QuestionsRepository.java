package com.example.be_swp.Repository;

import com.example.be_swp.Models.Questions;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends ListCrudRepository<Questions, Integer> {
}
