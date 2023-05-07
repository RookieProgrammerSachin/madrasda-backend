package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.CancelRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelRequestRepository extends JpaRepository<CancelRequest, Long> {

    void deleteByTransaction_Id(Long transactionId);
}