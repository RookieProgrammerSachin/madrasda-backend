package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.repositories.ProductImageRepository;
import com.example.madrasdaapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper {
     private final ProductRepository productRepository;
     private final ProductImageRepository productImageRepository;
     private final ProductImageMapper productImageMapper;
     private final ModelMapper mapper;

     public ProductDTO mapToDTO(Product product) {


          ProductDTO item = new ProductDTO();
          item.setId(product.getId());
          item.setName(product.getName());
          item.setBaseprice(product.getBasePrice());
          item.setDiscount(product.getDiscount());
          item.setShipping(product.getShipping());
          item.setTotal(product.getTotal());
          item.setTax(product.getTax());
          item.setProfit(product.getProfit());
          item.setImages(product.getProductImages()
                  .stream()
                  .map(productImageMapper::getImagePath)
                  .toList()
          );
          item.setColors(product.getColors());
          item.setSizes(product.getSizes());
          return item;
     }

     public Product mapToEntity(ProductDTO productDTO) {
          mapper.getConfiguration()
                  .setMatchingStrategy(MatchingStrategies.STRICT)
                  .setSkipNullEnabled(true);
          Product product = productRepository.findById(productDTO.getId()).orElse(new Product());
          mapper.createTypeMap(ProductDTO.class, Product.class)
                  .addMapping(ProductDTO::getImages, Product::setProductImages)
                  .map(productDTO, product);
          return product;

     }
}
