package com.example.be_swp.Repository;

import com.example.be_swp.Models.ExpertRatings;
import com.example.be_swp.Models.ServiceRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRatingsRepository extends JpaRepository<ServiceRatings, Long> , JpaSpecificationExecutor<ServiceRatings> {


    Service findByServiceId(Long id);

    List<ServiceRatings> findAllByServiceId(Long id);
}
