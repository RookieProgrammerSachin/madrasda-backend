package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}