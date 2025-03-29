package com.example.be_swp.Repository;

import com.example.be_swp.Models.ExpertOccupiedTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

    @Repository
    public interface ExpertOccupiedTimeRepository extends JpaRepository<ExpertOccupiedTimes, Long>, JpaSpecificationExecutor<ExpertOccupiedTimes>

     {


         @Query("SELECT e FROM ExpertOccupiedTimes e " +
                 "WHERE e.expertId = :expertId " +
                 "AND e.date = :date " +
                 "AND :startAt BETWEEN e.startAt AND e.endAt")
         List<ExpertOccupiedTimes> findOccupiedTimes(
                 @Param("expertId") Long expertId,
                 @Param("date") LocalDate date,
                 @Param("startAt") Long startAt
         );





     }
