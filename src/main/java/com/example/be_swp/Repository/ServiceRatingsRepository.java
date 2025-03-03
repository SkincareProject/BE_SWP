package com.example.be_swp.Repository;

import com.example.be_swp.Models.ExpertRatings;
import com.example.be_swp.Models.ServiceRatings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRatingsRepository extends ListCrudRepository<ServiceRatings, Integer> {
    @Query(value = "SELECT sr FROM ServiceRatings sr WHERE sr.services.serviceId = :serviceId")
    public Optional<ServiceRatings> findByServiceId(@Param("serviceId") int serviceId);

}
