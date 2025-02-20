package com.example.be_swp.Repository;

import com.example.be_swp.Models.Appointments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends ListCrudRepository<Appointments, Integer> {
    @Query(value = "SELECT e FROM Appointments e WHERE e.users.id = ?1")
    Optional<Appointments> findByUserId(int user_id);

    @Query(value = "SELECT ex FROM Appointments ex WHERE ex.users.fullName LIKE %:name%")
    List<Appointments> findByName(@Param("name") String name);
}
