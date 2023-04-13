package com.example.madrasdaapi.dto.ClientDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link CartItem} entity
 */
@Getter
@Setter
public class TransientCartItemDTO implements Serializable {
    private Long id;
    private CartItemProduct product;
    private Integer quantity;
}