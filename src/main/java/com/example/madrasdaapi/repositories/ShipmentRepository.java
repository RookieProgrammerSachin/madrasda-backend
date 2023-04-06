package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findByTransaction_Id(Long transactionId);
}