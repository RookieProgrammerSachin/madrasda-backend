package com.example.madrasdaapi.dto.ClientDTO;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.CartItem} entity
 */
@Getter
@Setter
public class CartItemDTO implements Serializable {
     private Long id;
     private ProductDTO product;
     private Integer quantity;
}