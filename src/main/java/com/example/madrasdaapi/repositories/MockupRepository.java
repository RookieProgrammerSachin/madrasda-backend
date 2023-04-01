package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Mockup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockupRepository extends JpaRepository<Mockup, Long> {
}