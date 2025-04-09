package com.example.be_swp.Repository;

import com.example.be_swp.DTOs.ExpertsDTO;
import com.example.be_swp.Models.Experts;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<Experts, Long>, JpaSpecificationExecutor<Experts> {

    Experts findByExpertId(Long expertId);
}
