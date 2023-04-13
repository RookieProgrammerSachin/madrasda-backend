package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.ClientDTO.CartDTO;
import com.example.madrasdaapi.dto.ClientDTO.CartItemDTO;
import com.example.madrasdaapi.dto.ClientDTO.CartItemProduct;
import com.example.madrasdaapi.dto.ClientDTO.TransientCartItemDTO;
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

     public TransientCartItemDTO mapToDTO(CartItem cartItem) {
          return mapper.map(cartItem, TransientCartItemDTO.class);
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

     public CartItemDTO mapToCartItemDTO(CartItem cartItem) {
          CartItemDTO cartItemDto = new CartItemDTO();
          cartItemDto.setId(cartItemDto.getId());
          cartItemDto.setQuantity(cartItem.getQuantity());
          CartItemProduct product = new CartItemProduct();
          product.setName(cartItem.getProduct().getName());
          product.setDiscount(cartItem.getProduct().getDiscount().floatValue());
          product.setTotal(cartItem.getProduct().getTotal().floatValue());
          product.setColorDTO(mapper.map((cartItem.getSku().getColor()), ColorDTO.class));
          product.setSizeDTO(mapper.map(cartItem.getSku().getSize(), SizeDTO.class));
          cartItemDto.setProduct(product);
          return cartItemDto;
     }
}
