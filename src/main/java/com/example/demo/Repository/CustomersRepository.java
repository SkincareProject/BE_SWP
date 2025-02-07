package com.example.demo.Repository;

import com.example.demo.Models.Customers;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends ListCrudRepository<Customers,Integer> {
}
