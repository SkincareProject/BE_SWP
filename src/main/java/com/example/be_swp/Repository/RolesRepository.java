package com.example.be_swp.Repository;

import com.example.be_swp.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends ListCrudRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);

}
