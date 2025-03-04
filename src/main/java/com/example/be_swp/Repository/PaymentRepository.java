package com.example.be_swp.Repository;

import com.example.be_swp.Models.Payments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends ListCrudRepository<Payments,Integer> {

    @Query(value = "SELECT pm FROM Payments pm WHERE pm.appointments.appointmentId = :appointmentId ")
    public Optional<Payments> findByAppointmentId(@Param("appointmentId") int appointmentId);

}
