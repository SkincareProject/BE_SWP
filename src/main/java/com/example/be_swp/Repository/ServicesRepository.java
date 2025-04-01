package com.example.be_swp.Repository;

import com.example.be_swp.Models.Services;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicesRepository extends ListCrudRepository<Services,Integer> {

    @Query(value = "SELECT sv FROM Services sv WHERE sv.skinType LIKE %:skinType% ")
    public Optional<Services> findBySkinType(@Param("skinType") String skinType);
}
