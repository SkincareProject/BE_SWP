package com.example.be_swp.Repository;

import com.example.be_swp.Models.WorkSchedule;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkScheduleRepository extends ListCrudRepository<WorkSchedule,Integer> {
}
