package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Design;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignRepository extends JpaRepository<Design, Long> {
     Optional<Design> findByIdAndVendor_Id(Long id, Long vendorId);

    void deleteByIdAndVendor_Id(Long designId, Long vendorId);
}