package com.example.be_swp.Repository;

import com.example.be_swp.Models.Roles;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends ListCrudRepository<Roles,Integer> {

}
