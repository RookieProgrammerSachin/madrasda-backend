package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.CartDTO;
import com.example.madrasdaapi.dto.CartItemDTO;
import com.example.madrasdaapi.models.CartItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

     private final ModelMapper mapper;

     public CartItemDTO mapToDTO(CartItem cartItem) {
          return mapper.map(cartItem, CartItemDTO.class);
     }

     public CartItem mapToEntity(CartDTO cartDTO) {
          CartItem cartItem = new CartItem();
          mapper.map(cartItem, CartItem.class);
          return cartItem;
     }
}
