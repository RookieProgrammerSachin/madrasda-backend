package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import com.example.madrasdaapi.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
     @Procedure(name = "getSalesAnalysisByVendorId")
     SalesAnalysis getSalesAnalysisByVendorId(@Param("vendorId") Long vendorId);

     @Procedure(name = "TOP_SELLERS_FOR_VENDOR")
     List<Object[]> TOP_SELLERS_FOR_VENDOR(@Param("vendor_id") Long vendorId);

}