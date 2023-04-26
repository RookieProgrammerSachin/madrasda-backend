package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
     Page<Transaction> findAllByOrderByOrderDateDesc(Pageable pageable);
     List<Transaction> findByBillingUser_Id(Long id);
     Optional<Transaction> findByOrderId(String orderId);
     Optional<Transaction> findByPaymentId(String paymentId);

     List<Transaction> findByBillingUser_Phone(String phone);

     List<Transaction> findByBillingUser_PhoneAndPaymentStatusLike(String phone, String paymentStatus);
}