package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
     List<Transaction> findByCustomer_Id(Long id);
}