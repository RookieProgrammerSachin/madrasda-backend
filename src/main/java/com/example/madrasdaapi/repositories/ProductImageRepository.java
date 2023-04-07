package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
        List<ProductImage> findByProductId(Long id);

}