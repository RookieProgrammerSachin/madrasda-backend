package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.dto.commons.NewProductDTO;
import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.mappers.ProductMapper;
import com.example.madrasdaapi.mappers.TemplateMapper;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.TemplateRepository;
import com.example.madrasdaapi.services.VendorServices.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

     public ProductDTO createProduct(NewProductDTO newProduct) {
          Product product = productMapper.mapToEntity(newProduct);
          product.setPublishStatus(false);
          return productMapper.mapToDTO(productRepository.save(product));

     }

     public Page<ProductDTO> getProductsByVendor(Long vendorId, Integer pageNo, Integer pageSize) {
          Page<Product> products = productRepository.findByVendor_Id(vendorId, PageRequest.of(pageNo, pageSize));
          return products.map(productMapper::mapToDTO);
     }
     public Page<ProductDTO> getAllProducts(int pageNo, int pageSize) {
          return productRepository.findAll(PageRequest.of(pageNo, pageSize)).map(productMapper::mapToDTO);
     }
     public Page<ProductDTO> getByAudience(int pageNo, int pageSize, String audience) {
          return productRepository.findAllByAudience(audience, PageRequest.of(pageNo, pageSize))
                  .map(productMapper::mapToDTO);
     }
     public Page<ProductDTO> searchProducts(int pageNo, int pageSize, String searchTerm) {
          return productRepository.findByNameOrAudienceOrMockup_name(
                  searchTerm,
                  searchTerm,
                  searchTerm,
                  PageRequest.of(pageNo, pageSize)
          ).map(productMapper::mapToDTO);
     }
}
