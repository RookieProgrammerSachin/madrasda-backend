package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByProduct_IdAndSku_Sku(Long id, String sku);

    List<CartItem> findByCustomer_Phone(String phone);

    void deleteByCustomer_Id(Long id);
}