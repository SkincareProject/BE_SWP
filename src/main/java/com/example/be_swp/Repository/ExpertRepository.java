package com.example.be_swp.Repository;

import com.example.be_swp.Models.Experts;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends ListCrudRepository<Experts,Integer> {
}
