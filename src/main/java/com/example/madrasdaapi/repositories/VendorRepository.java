package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import com.example.madrasdaapi.models.Template;
import com.example.madrasdaapi.models.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
     @Query(value = "CALL VENDOR_SALES(:vendorId);", nativeQuery = true)
     SalesAnalysis getSalesAnalysisByVendorId(Long vendorId);
}