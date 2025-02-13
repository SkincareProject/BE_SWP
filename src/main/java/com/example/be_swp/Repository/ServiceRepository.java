package com.example.be_swp.Repository;

import com.example.be_swp.Models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
    List<Services> findByServiceId(Long id);
    List<Services> findByServiceName(String name);
}
