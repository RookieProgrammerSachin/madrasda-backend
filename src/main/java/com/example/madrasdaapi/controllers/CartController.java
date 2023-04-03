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

     @GetMapping("{id}")
     public void getCartItemsForCustomer(@PathVariable Long id) {
//          return cartService.getCartForCustomer(id);
     }

     @DeleteMapping("{customerId}&{productId}")
     public void removeFromCart(@PathVariable Long customerId, @PathVariable Long productId) {

     }
}
