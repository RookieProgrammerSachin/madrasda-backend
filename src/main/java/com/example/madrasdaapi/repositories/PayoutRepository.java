package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.PayoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayoutRepository extends JpaRepository<PayoutRecord, Long> {
    Optional<PayoutRecord> findByVendor_IdAndPaid(Long id, Boolean paid);
}
