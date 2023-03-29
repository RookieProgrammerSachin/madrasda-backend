package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}