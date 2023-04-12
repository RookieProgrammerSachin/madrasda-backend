package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.ClientDTO.CartDTO;
import com.example.madrasdaapi.dto.ClientDTO.CartItemDTO;
import com.example.madrasdaapi.dto.commons.ColorDTO;
import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.dto.commons.SizeDTO;
import com.example.madrasdaapi.models.*;
import com.example.madrasdaapi.repositories.ProductSKUMappingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

     private final ModelMapper mapper;
     private final ProductSKUMappingRepository productSKUMappingRepository;
     public CartItemDTO mapToDTO(CartItem cartItem) {
          CartItemDTO cartItemDTO = mapper.map(cartItem, CartItemDTO.class);
          ProductDTO product = cartItemDTO.getProduct();
          ProductSKUMapping sku = cartItem.getSku();
          product.getColors().get(0).getSizes().get(0).setSku(sku.getSku());
          cartItemDTO.setProduct(product);
          return cartItemDTO;
     }
     public CartItemDTO mapToCartItemDTO(CartItem cartItem) {
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
