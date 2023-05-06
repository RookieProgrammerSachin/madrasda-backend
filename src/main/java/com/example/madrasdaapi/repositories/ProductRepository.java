package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
     @Transactional
     @Modifying
     @Query(value = "update product p set p.publish_status = p.publish_status XOR true where p.id = ?1 AND p.vendor_id = ?2" ,nativeQuery = true)
     void updatePublishStatusByIdAAndVendor_Id(Long id, Long vendorId);

     Page<Product> findAllByMockup_Id(Long mockupId, Pageable pageable);
     Page<Product> findByVendor_Id(Long vendorId, Pageable pageable);
     Page<Product> findAllByAudience(String audience, Pageable pageable);

     Page<Product> findByNameOrAudienceOrMockup_name(String name,String audience,String mockupName, Pageable pageable);

    Page<Product> findByVendor_IdAndPublishStatus(Long vendorId, boolean b, Pageable pageable);

    List<Product> deleteByAudience(String fanHouse);

}