package com.example.be_swp.Repository;

import com.example.be_swp.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends ListCrudRepository<Users,Integer> {
    Optional<Users> findByPhone(String phone);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
    Optional<Users> findById(int id);

}
