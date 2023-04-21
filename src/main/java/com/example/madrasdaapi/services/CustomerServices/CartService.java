package com.example.madrasdaapi.services.CustomerServices;

import com.example.madrasdaapi.dto.ClientDTO.CartDTO;
import com.example.madrasdaapi.dto.commons.ColorDTO;
import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.mappers.CartItemMapper;
import com.example.madrasdaapi.models.CartItem;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.repositories.*;
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

    private final ProductSKUMappingRepository productSKUMappingRepository;

    public CartDTO getCartForCustomer(String phone) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User", "phone", phone));

        return new CartDTO(user.getId(), user.getCart()
                .stream()
                .map(cartItemMapper::mapToCartItemDTO)
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

    public void addToCart(String phone, ProductDTO productDTO) {
        User customer = userRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User", "phone", phone));
        Optional<CartItem> item = cartItemRepository.findByProduct_IdAndSku_Sku(productDTO.getId(), productDTO.getColors().get(0).getSizes().get(0).getSku());

        CartItem cartItem;
        if (item.isPresent()) {
            cartItem = item.get();
            cartItem.setQuantity(productDTO.getQuantity());
        } else {
            Product product = productRepository.findById(productDTO.getId()).get();
            ColorDTO colorDTO = productDTO.getColors().get(0);
            cartItem = new CartItem();
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);
            cartItem.setQuantity(productDTO.getQuantity());
            cartItem.setSku(productSKUMappingRepository.findBySku(
                    colorDTO.getSizes().get(0).getSku()
            ));
        }
        cartItemRepository.save(cartItem);
    }
}
