package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    long deleteByProduct_Id(Long id);
        List<ProductImage> findByProductId(Long id);

    long deleteAllByProduct_Id(Long id);
}