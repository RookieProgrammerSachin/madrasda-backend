package com.example.madrasdaapi.dto.commons;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.OrderItem} entity
 */
@Getter
@Setter
public class OrderItemDTO implements Serializable {
     private Long id;
     private int quantity;
     private String sku;
     private ProductDTO product;
}