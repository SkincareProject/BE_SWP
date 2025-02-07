package com.example.demo.Repository;

import com.example.demo.Models.Roles;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends ListCrudRepository<Roles,Integer> {

}
