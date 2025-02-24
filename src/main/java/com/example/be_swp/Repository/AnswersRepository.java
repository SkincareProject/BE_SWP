package com.example.be_swp.Repository;

import com.example.be_swp.Models.Answers;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends ListCrudRepository<Answers, Integer> {
}
