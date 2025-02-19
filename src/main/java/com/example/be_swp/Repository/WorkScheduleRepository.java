package com.example.be_swp.Repository;

import com.example.be_swp.Models.WorkSchedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkScheduleRepository extends ListCrudRepository<WorkSchedule,Integer> {

    @Query(value = "SELECT ws FROM WorkSchedule ws WHERE ws.experts.users.fullName LIKE %:name%")
    public List<WorkSchedule> findByExpertName(@Param("name") String name);

    @Query(value = "SELECT ws FROM WorkSchedule ws WHERE ws.experts.expertId = :id")
    public List<WorkSchedule> findByExpertId(@Param("id") int id);

    @Query(value = "SELECT ws FROM WorkSchedule ws WHERE ws.work_date = :today")
    public List<WorkSchedule> findAllToday(@Param("today") LocalDate today);

    @Query(value = "SELECT ws FROM WorkSchedule ws WHERE ws.work_date = :today AND ws.experts.expertId = :expertId")
    public List<WorkSchedule> findAllTodayByExpertId(@Param("today") LocalDate today, @Param("expertId") int expertId);
}
