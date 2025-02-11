package com.example.be_swp.Repository;

import com.example.be_swp.Models.PaymentMethods;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends ListCrudRepository<PaymentMethods, Integer> {
}
