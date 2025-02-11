package com.example.be_swp.Repository;

import com.example.be_swp.Models.Services;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends ListCrudRepository<Services,Integer> {
}
