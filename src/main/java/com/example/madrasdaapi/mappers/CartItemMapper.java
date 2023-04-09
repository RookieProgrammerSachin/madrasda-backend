package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.ClientDTO.CartDTO;
import com.example.madrasdaapi.dto.ClientDTO.CartItemDTO;
import com.example.madrasdaapi.dto.commons.ColorDTO;
import com.example.madrasdaapi.dto.commons.SizeDTO;
import com.example.madrasdaapi.models.CartItem;
import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.Size;
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

     public Color mapToEntity(ColorDTO colorDTO) {
          return mapper.map(colorDTO, Color.class);
     }

     public Size mapToEntity(SizeDTO sizeDTO) {
          return mapper.map(sizeDTO, Size.class);
     }
}
