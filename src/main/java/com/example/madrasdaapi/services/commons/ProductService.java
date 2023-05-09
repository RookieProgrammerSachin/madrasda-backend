package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.commons.NewProductDTO;
import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.mappers.ProductMapper;
import com.example.madrasdaapi.mappers.TemplateMapper;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.TemplateRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import com.example.madrasdaapi.services.VendorServices.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
     private final ProductRepository productRepository;
     private final TemplateMapper templateMapper;
     private final TemplateRepository templateRepository;
     private final TemplateService templateService;
     private final ProductMapper productMapper;
     private final VendorRepository vendorRepository;

     public void togglePublishState(Long productId) {
          Long vendorId = vendorRepository.findIdByUser_Email(AuthContext.getCurrentUser());
          try {
               productRepository.updatePublishStatusByIdAndVendorId(productId, vendorId);
          }
          catch(Exception e){
               throw new APIException("Admin Ban Present", HttpStatus.CONFLICT);
          }
     }

     public ProductDTO createProduct(NewProductDTO newProduct) {
          Product product = productMapper.mapToEntity(newProduct);

          product.setPublishStatus(false);
          return productMapper.mapToDTO(productRepository.save(product));
     }

     public Page<ProductDTO> getProductsByVendor(Long vendorId, Integer pageNo, Integer pageSize) {
          Page<Product> products = productRepository.findByVendor_IdAndVendor_StatusAndPublishStatus(vendorId, true, true, PageRequest.of(pageNo, pageSize));
          return products.map(productMapper::mapToDTO);
     }
     public Page<ProductDTO> getAllProducts(int pageNo, int pageSize) {
          return productRepository.findAll(PageRequest.of(pageNo, pageSize)).map(productMapper::mapToDTO);
     }
     public Page<ProductDTO> getByAudience(int pageNo, int pageSize, String audience) {
          return productRepository.findAllByAudienceAndVendor_StatusAndPublishStatus(audience, true, true, PageRequest.of(pageNo, pageSize))
                  .map(productMapper::mapToDTO);
     }
     public Page<ProductDTO> searchProducts(int pageNo, int pageSize, String searchTerm) {
          return productRepository.findByNameOrAudienceOrMockup_nameAndVendor_StatusAndPublishStatus(
                  searchTerm,
                  searchTerm,
                  searchTerm,
                  true, true, PageRequest.of(pageNo, pageSize)
          ).map(productMapper::mapToDTO);
     }
     public ProductDTO getProductDetails(Long id) {
          return productMapper.mapToDTO(productRepository.findById(id).orElseThrow(
                  () -> new APIException("Product not found", HttpStatus.BAD_REQUEST)
          ));
     }

     public Page<ProductDTO> getAllProductsByVendor(Long vendorId, Integer pageNo, Integer pageSize) {
          Page<Product> products = productRepository.findByVendor_IdAndPublishStatus(vendorId, true, PageRequest.of(pageNo, pageSize));
          return products.map(productMapper::mapToDTO);
     }

     public Page<ProductDTO> getProductsByMockupId(Long mockupId, Integer pageNo, Integer pageSize) {
          Page<Product> products = productRepository.findAllByMockup_IdAndVendor_StatusAndPublishStatus(mockupId, true, true, PageRequest.of(pageNo, pageSize));
          return products.map(productMapper::mapToDTO);
     }
}
