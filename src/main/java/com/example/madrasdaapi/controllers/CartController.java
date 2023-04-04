package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.CartDTO;
import com.example.madrasdaapi.services.CustomerServices.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/")
@RequiredArgsConstructor
public class CartController {
     private final CartService cartService;

     @GetMapping("{customerId}")
     public CartDTO getCartItemsForCustomer(@PathVariable Long customerId) {
          return cartService.getCartForCustomer(customerId);
     }

     @PutMapping("changeQuantity/{cartItemId}&&{quantity}")
     public void changeCartItemQuantity(@PathVariable Long cartItemId, @PathVariable Integer quantity) {
          cartService.changeQuantity(cartItemId, quantity);
     }
     @PostMapping("addToCart/{customerId}&&{productId}")
     public void addToCart(@PathVariable Long customerId, @PathVariable Long productId) {
          cartService.addToCart(customerId, productId);
     }
}
