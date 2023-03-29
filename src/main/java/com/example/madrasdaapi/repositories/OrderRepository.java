package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}