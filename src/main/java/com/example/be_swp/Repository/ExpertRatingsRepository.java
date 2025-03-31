package com.example.be_swp.Repository;

import com.example.be_swp.Models.ExpertRatings;
import com.example.be_swp.Models.ServiceRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertRatingsRepository extends JpaRepository<ExpertRatings, Long>, JpaSpecificationExecutor<ExpertRatings> {

//    @Query(value = "SELECT er FROM ExpertRatings er WHERE er.experts.expertId = :expertId")
//    public Optional<ExpertRatings> findByExpertId(@Param("expertId") int expertId);

}
