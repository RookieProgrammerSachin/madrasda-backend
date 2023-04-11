package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.PayoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<PayoutRecord, Long> {

}
