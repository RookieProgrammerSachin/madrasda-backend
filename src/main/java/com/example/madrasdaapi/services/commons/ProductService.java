package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.mappers.ProductMapper;
import com.example.madrasdaapi.mappers.TemplateMapper;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.TemplateRepository;
import com.example.madrasdaapi.services.VendorServices.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
     private final ProductRepository productRepository;
     private final TemplateMapper templateMapper;
     private final TemplateRepository templateRepository;
     private final TemplateService templateService;
     private final ProductMapper productMapper;

     public void togglePublishState(Long productId) {
          productRepository.updatePublishStatusById(productId);
     }

     public ProductDTO createProduct(ProductDTO productDTO) {
          Product product = productMapper.mapToEntity(productDTO);
          product.setPublishStatus(false);
          return productMapper.mapToDTO(productRepository.save(product));

     }
}
