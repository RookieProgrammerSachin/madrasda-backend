package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarouselRepository extends JpaRepository<Carousel, Long> {
}