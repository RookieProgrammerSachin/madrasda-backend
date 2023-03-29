package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}