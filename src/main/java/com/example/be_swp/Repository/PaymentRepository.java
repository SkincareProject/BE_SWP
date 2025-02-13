package com.example.be_swp.Repository;

import com.example.be_swp.Models.Payments;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends ListCrudRepository<Payments,Integer> {
}
