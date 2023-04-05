package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.services.commons.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/")
@RequiredArgsConstructor
public class ProductController {
     private final ProductService productService;

     @PutMapping("togglePublishState/{productId}")
     public void togglePublishState(@PathVariable Long productId) {
          productService.togglePublishState(productId);
     }

     @PostMapping("createProduct")
     public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
          return productService.createProduct(productDTO);
     }
}
