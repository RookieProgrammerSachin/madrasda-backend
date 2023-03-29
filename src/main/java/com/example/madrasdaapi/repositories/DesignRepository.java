package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Design;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<Design, Long> {
}