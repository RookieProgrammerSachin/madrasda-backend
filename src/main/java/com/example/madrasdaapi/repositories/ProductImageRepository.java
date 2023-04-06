package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
        Optional<List<ProductImage>> findAllByProductId(Long id);
}