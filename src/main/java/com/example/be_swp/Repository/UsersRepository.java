package com.example.be_swp.Repository;

import com.example.be_swp.Models.Users;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends ListCrudRepository<Users,Integer> {

}
