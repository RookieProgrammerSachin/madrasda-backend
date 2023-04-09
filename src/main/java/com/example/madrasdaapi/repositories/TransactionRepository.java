package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
     List<Transaction> findByBillingUser_Id(Long id);
     Optional<Transaction> findByOrderId(String orderId);
}