package com.example.madrasdaapi.services.CustomerServices;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.mappers.ProductMapper;
import com.example.madrasdaapi.repositories.CustomerRepository;
import com.example.madrasdaapi.repositories.ProductImageRepository;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
     private final UserRepository userRepository;
     private final CustomerRepository customerRepository;

     private final ProductRepository productRepository;
     private final ProductImageRepository productImageRepository;
     private final ProductMapper productMapper;

     public Page<ProductDTO> getAllProducts(int pageNo, int pageSize) {
          return productRepository.findAll(PageRequest.of(pageNo, pageSize)).map(productMapper::mapProduct);
     }

}