package com.example.be_swp.Repository;

import com.example.be_swp.Models.Appointments;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends ListCrudRepository<Appointments, Integer> {
}
