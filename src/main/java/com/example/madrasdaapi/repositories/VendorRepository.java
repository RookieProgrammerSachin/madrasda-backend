package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}