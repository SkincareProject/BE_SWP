package com.example.be_swp.Repository;

import com.example.be_swp.Models.Experts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertRepository extends ListCrudRepository<Experts,Integer> {

    @Query(value = "SELECT * FROM EXPERTS WHERE user_id = ?1", nativeQuery = true)
    Optional<Experts> findByUserId(int user_id);

}
