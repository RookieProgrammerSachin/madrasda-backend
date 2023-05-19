package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.ProductSKUMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ProductSKUMappingRepository extends JpaRepository<ProductSKUMapping, Long> {
    List<ProductSKUMapping> findByMockup_IdAndColor_IdIn(Long id, List<Long> ids);
    ProductSKUMapping findByMockup_IdAndColor_IdAndSize_Id(Long mockupId, Long colorId, Long sizeId);

    ProductSKUMapping findBySku(String sku);

    @Modifying
    @Query("update ProductSKUMapping p set p.status = (NOT p.status) where p.color.id = ?1 and p.mockup.id = ?2")
    int updateStatusByColor(Long colorId, Long mockupId);

}