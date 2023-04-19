package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByIdAndIsBillingUser(Long id, Boolean isBillingUser);
}