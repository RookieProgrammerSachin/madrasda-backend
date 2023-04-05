package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}