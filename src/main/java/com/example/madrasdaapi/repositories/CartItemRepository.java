package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.CartItem;
import com.example.madrasdaapi.models.ProductSKUMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
     Optional<CartItem> findByProduct_Id(Long cartId);

    Optional<CartItem> findByProduct_IdAndCustomer_Id(Long productId, Long customerId);

    Optional<CartItem> findBySku_Sku(String sku);

    Optional<CartItem> findByProduct_IdAndSku_Sku(Long id, String sku);
}