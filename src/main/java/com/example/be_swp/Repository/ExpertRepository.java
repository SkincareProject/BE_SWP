package com.example.be_swp.Repository;

import com.example.be_swp.Models.Experts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertRepository extends ListCrudRepository<Experts,Integer> {

    @Query(value = "SELECT e FROM Experts e WHERE e.users.id = ?1")
    Optional<Experts> findByUserId(int user_id);

    @Query(value = "SELECT ex FROM Experts ex WHERE ex.users.fullName LIKE %:name%")
    List<Experts>findByName(@Param("name") String name);

}
