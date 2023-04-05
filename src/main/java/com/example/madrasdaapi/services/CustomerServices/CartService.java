package com.example.madrasdaapi.services.CustomerServices;

import com.example.madrasdaapi.dto.CartDTO;
import com.example.madrasdaapi.mappers.CartItemMapper;
import com.example.madrasdaapi.models.CartItem;
import com.example.madrasdaapi.models.Customer;
import com.example.madrasdaapi.repositories.CartItemRepository;
import com.example.madrasdaapi.repositories.CustomerRepository;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CartService {
     private final CartItemRepository cartItemRepository;
     private final UserRepository userRepository;
     private final CartItemMapper cartItemMapper;
     private final CustomerRepository customerRepository;
     private final ProductRepository productRepository;

     public CartDTO getCartForCustomer(Long customerId) {
          Customer user = customerRepository.findById(customerId).get();

          return new CartDTO(user.getId(), user.getCart()
                  .stream()
                  .map(cartItemMapper::mapToDTO)
                  .collect(Collectors.toList()));
     }

     public void changeQuantity(Long cartItemId, Integer quantity) {
          CartItem cartItem = cartItemRepository.findById(cartItemId).get();
          if (quantity < 0) throw new RuntimeException("quantity less than 0");
          else if (quantity == 0) cartItemRepository.deleteById(cartItemId);
          else {
               cartItem.setQuantity(quantity);
               cartItemRepository.save(cartItem);
          }
     }

     public void addToCart(Long customerId, Long productId) {
          Customer customer = customerRepository.findById(customerId).get();
          Optional<CartItem> item = cartItemRepository.findByProduct_Id(productId);
          CartItem cartItem;
          if(item.isPresent()){
               cartItem = item.get();
               cartItem.setQuantity(cartItem.getQuantity() + 1);
          }
          else {
               cartItem = new CartItem();
               cartItem.setCustomer(customer);
               cartItem.setProduct(productRepository.findById(productId).get());
               cartItem.setQuantity(1);
          }
          cartItemRepository.save(cartItem);
     }
}
