package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
     @Transactional
     @Modifying
     @Query(value = "update product p set p.publish_status = p.publish_status XOR true where p.id = ?1" ,nativeQuery = true)
     void updatePublishStatusById(Long id);

}