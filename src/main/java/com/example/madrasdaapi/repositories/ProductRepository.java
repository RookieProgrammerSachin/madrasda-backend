package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.models.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
     @Transactional
     @Modifying
     @Query("UPDATE Product p SET p.publishStatus = (NOT p.publishStatus) WHERE p.id = ?1 AND p.vendor.id = ?2 AND p.adminBan = false")
     int updatePublishStatusByIdAndVendorId(Long id, Long vendorId);

    @Transactional
    @Modifying
    @Query("update Product p set p.publishStatus = (NOT p.publishStatus) where p.vendor.id = ?1 and p.id = ?2 and p.adminBan = false")
    int updatePublishStatusByVendor_IdAndIdAndAdminBanFalse(Long vendorId, Long id);


    Page<Product> findAllByMockup_IdAndVendor_StatusAndPublishStatusAndMockupDisabled(Long mockupId,boolean vendorStatus, boolean publishStatus, boolean mockupDisabled, Pageable pageable);

     Page<Product> findByVendor_Id(Long vendorId, Pageable pageable);

    Optional<Product> findByIdAndMockupDisabledAndAdminBan(Long aLong, boolean mockupDisabled, boolean adminBan);

    Page<Product> findAllByAudienceAndVendor_StatusAndPublishStatus(String audience, boolean vendorStatus, boolean publishStatus, Pageable pageable);


     Page<Product> findByNameOrAudienceOrMockup_nameAndVendor_StatusAndPublishStatus(String name,String audience,String mockupName, boolean status,
                                                                                     boolean publishStatus, Pageable pageable);

    Page<Product> findByVendor_IdAndVendor_StatusAndPublishStatusAndMockupDisabled(Long vendorId, boolean vendorStatus, boolean publishStatus, boolean mockupStatus, Pageable pageable);

    List<Product> deleteByAudience(String fanHouse);

    @Modifying
    @Query("UPDATE Product p set p.adminBan = (NOT p.adminBan), p.publishStatus = false where p.id = :productId")
    void toggleProductStatus(Long productId);


    Page<Product> findByVendor_IdAndPublishStatus(Long vendorId, boolean publishStatus, Pageable pageable);
}