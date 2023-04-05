package com.example.madrasdaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 */
@Getter
@Setter
@AllArgsConstructor
public class CartDTO implements Serializable {
     private Long id;
     private List<CartItemDTO> cartItems;
}