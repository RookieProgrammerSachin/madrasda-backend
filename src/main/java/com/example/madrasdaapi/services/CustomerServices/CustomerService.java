package com.example.madrasdaapi.services.CustomerServices;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.mappers.ProductMapper;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
     private final UserRepository userRepository;
     private final CustomerRepository customerRepository;
     private final VendorRepository vendorRepository;

     private final ProductRepository productRepository;
     private final ProductImageRepository productImageRepository;
     private final ProductMapper productMapper;

     public Page<ProductDTO> getAllProducts(int pageNo, int pageSize) {
          return productRepository.findAll(PageRequest.of(pageNo,pageSize))
                  .map(productMapper::mapProduct);
     }
     public List<Long> getAllVendorId(){
          return vendorRepository.findAll().stream()
                  .map((Vendor::getId)).toList();
     }
     public List<List<ProductDTO>> getProductsByCategory(String category){
          return productRepository.findAllByAudience(category).stream().map(products -> {
               List<ProductDTO> items = new ArrayList<>();
               for(Product p : products){
                    items.add(productMapper.mapProduct(p));
               }
               return items;
          }).toList();
     }
}