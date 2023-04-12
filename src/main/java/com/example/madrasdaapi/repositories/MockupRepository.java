package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Mockup;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MockupRepository extends JpaRepository<Mockup, Long> {
}