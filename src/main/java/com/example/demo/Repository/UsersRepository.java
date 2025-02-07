package com.example.demo.Repository;

import com.example.demo.Models.Users;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends ListCrudRepository<Users,Integer> {

}
