package com.example.be_swp.Repository;

import com.example.be_swp.Models.Customers;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends ListCrudRepository<Customers,Integer> {
}
