package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCustomer_PhoneAndProduct_IdAndSku_Sku(String phone, Long id, String sku);
    Optional<CartItem> findByCustomer_EmailOrCustomer_PhoneAndProduct_IdAndSku_Sku(String phone, String email, Long id, String sku);

    List<CartItem> findByCustomer_Phone(String phone);

    void deleteByCustomer_Id(Long id);
}