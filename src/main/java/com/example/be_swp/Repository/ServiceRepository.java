package com.example.be_swp.Repository;

import com.example.be_swp.Models.Service;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends ListCrudRepository<Service,Integer> {
}
