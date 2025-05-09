package com.example.be_swp.Repository;

import com.example.be_swp.DTOs.PaymentDTO;
import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Models.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long>, JpaSpecificationExecutor<Payments>   {


    Payments findByAppointmentId(Long id);

    List<PaymentDTO> findAllByUserId(Long userId);
}
