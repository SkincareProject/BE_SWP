package com.example.be_swp.Repository;

import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Models.Experts;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Long>, JpaSpecificationExecutor<Appointments>{

    List<Appointments> findAllByUserId(Long userId);

    Appointments findByAppointmentId(Long id);

    List<Appointments> findAllByExpertId(Long expertId);
}
