package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.ProductSKUMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSKUMappingRepository extends JpaRepository<ProductSKUMapping, Long> {
    List<ProductSKUMapping> findByMockup_IdAndColor_IdIn(Long id, List<Long> ids);
    ProductSKUMapping findByMockup_IdAndColor_IdAndSize_Id(Long mockupId, Long colorId, Long sizeId);

    ProductSKUMapping findBySku(String sku);
}