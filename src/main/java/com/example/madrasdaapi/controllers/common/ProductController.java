package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.services.commons.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

     @GetMapping("getProductsByVendor/{vendorId}")
     public Page<ProductDTO> getProductsByVendor(@PathVariable Long vendorId,
                                                  @RequestParam Integer pageNo,
                                                 @RequestParam Integer pageSize) {
          return productService.getProductsByVendor(vendorId, pageNo, pageSize);
     }
}
