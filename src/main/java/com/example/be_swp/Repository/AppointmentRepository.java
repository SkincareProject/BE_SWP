package com.example.be_swp.Repository;

import com.example.be_swp.Models.Appointments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends ListCrudRepository<Appointments, Integer> {
    @Query(value = "SELECT e FROM Appointments e WHERE e.users.id = ?1")
    List<Appointments> findByUserId(int user_id);

    @Query(value = "SELECT ex FROM Appointments ex WHERE ex.users.fullName LIKE %:name%")
    List<Appointments> findByName(@Param("name") String name);

    @Query(value = "SELECT a FROM Appointments a WHERE a.start_at = :timeStart AND a.end_at = :timeEnd")
    Optional<Appointments> findByTime(@Param("timeStart")LocalDateTime timeStart, @Param("timeEnd") LocalDateTime timeEnd);
}
