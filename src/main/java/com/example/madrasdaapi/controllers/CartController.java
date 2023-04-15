package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.ClientDTO.CartDTO;
import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.services.CustomerServices.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/")
@RequiredArgsConstructor
@CrossOrigin
public class CartController {
     private final CartService cartService;

     @GetMapping
     public CartDTO getCartItemsForCustomer() {
          return cartService.getCartForCustomer(AuthContext.getCurrentUser());
     }

     @PutMapping("changeQuantity/{cartItemId}&&{quantity}")
     public void changeCartItemQuantity(@PathVariable Long cartItemId, @PathVariable Integer quantity) {
          cartService.changeQuantity(cartItemId, quantity);
     }
     @PostMapping("addToCart")
     public void addToCart(@RequestBody ProductDTO productDTO) {
          cartService.addToCart(AuthContext.getCurrentUser(), productDTO);
     }
}
