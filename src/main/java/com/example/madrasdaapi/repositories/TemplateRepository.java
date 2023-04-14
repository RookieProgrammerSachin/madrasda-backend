package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
     Page<Template> findByVendor_Id(Long vendorId, Pageable pageable);

    Optional<Template> findByIdAndVendor_Id(Long id, Long vendorId);
}