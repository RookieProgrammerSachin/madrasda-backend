package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.dto.commons.NewProductDTO;
import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.dto.commons.ProductLadderItem;
import com.example.madrasdaapi.dto.commons.ProductSKUMappingDTO;
import com.example.madrasdaapi.mappers.ProductMapper;
import com.example.madrasdaapi.repositories.ProductSKUMappingRepository;
import com.example.madrasdaapi.services.commons.ProductService;
import com.example.madrasdaapi.utils.ProcedureCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
     private final ProductService productService;
     private final ProductMapper productMapper;
     private final ProcedureCaller caller;
     private final ProductSKUMappingRepository productSKUMappingRepository;
     @PutMapping("togglePublishState/{productId}")
     public void togglePublishState(@PathVariable Long productId) {
          productService.togglePublishState(productId);
     }

     @PostMapping("createProduct")
     public ProductDTO createProduct(@RequestBody NewProductDTO productDTO) {
          return productService.createProduct(productDTO);
     }
     @GetMapping("getProductDetails/{prodId}")
     public ProductDTO getProductDetails(
             @PathVariable Long prodId
     ) {
          return productService.getProductDetails(prodId);
     }

     @GetMapping("getProductsByVendor/{vendorId}")
     public Page<ProductDTO> getProductsByVendor(@PathVariable Long vendorId,
                                                  @RequestParam Integer pageNo,
                                                 @RequestParam Integer pageSize) {
          return productService.getProductsByVendor(vendorId, pageNo, pageSize);
     }

     @GetMapping("/topSellers/{vendorId}&{limit}")
     public List<ProductLadderItem> getTopSellers(@PathVariable Long vendorId, @PathVariable Integer limit) {
          return caller.getTopSellingProductsForVendor(vendorId, limit);
     }


     @GetMapping("getAllSKU")
     public Page<ProductSKUMappingDTO> productSKUMapping(@RequestParam(defaultValue = "0") Integer pageNo,
                                                         @RequestParam(defaultValue = "25") Integer pageSize){
          return productSKUMappingRepository.findAll(PageRequest.of(pageNo, pageSize)).map(productMapper::mapSKUToDTO);
     }
}
