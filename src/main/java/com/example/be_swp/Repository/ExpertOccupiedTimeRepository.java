package com.example.be_swp.Repository;

import com.example.be_swp.Models.ExpertOccupiedTimes;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertOccupiedTimeRepository extends ListCrudRepository<ExpertOccupiedTimes, Integer> {
}
