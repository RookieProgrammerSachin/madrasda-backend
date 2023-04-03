package com.example.madrasdaapi.dto;

import com.example.madrasdaapi.models.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Cart} entity
 */
@Getter
@Setter
public class CartDTO implements Serializable {
     private Long id;
     private List<CartItem> cartItems;
}