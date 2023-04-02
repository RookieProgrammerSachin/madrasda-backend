package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}