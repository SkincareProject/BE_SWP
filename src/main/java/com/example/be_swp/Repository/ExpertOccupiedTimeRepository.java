package com.example.be_swp.Repository;

import com.example.be_swp.Models.ExpertOccupiedTimes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertOccupiedTimeRepository extends ListCrudRepository<ExpertOccupiedTimes, Integer> {

    @Query(value = "SELECT eot FROM ExpertOccupiedTimes eot WHERE eot.experts.expertId = :expertId")
    public List<ExpertOccupiedTimes> findByExpertId(@Param("expertId") int expertId);

    @Query(value = "SELECT eot FROM ExpertOccupiedTimes eot WHERE eot.date = :today")
    public List<ExpertOccupiedTimes> findAllToday(@Param("today")LocalDate today);

    @Query(value = "SELECT eot FROM ExpertOccupiedTimes eot WHERE eot.date = :today AND eot.experts.expertId = :expertId")
    public List<ExpertOccupiedTimes> findAllTodayByExpertId(@Param("today")LocalDate today, @Param("expertId") int expertId);

}
