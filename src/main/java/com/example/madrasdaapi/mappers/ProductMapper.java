package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.dto.commons.ProductImageDTO;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.models.ProductImage;
import com.example.madrasdaapi.repositories.ProductImageRepository;
import com.example.madrasdaapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMapper {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;
    public ProductDTO mapProduct(Product product){

        List<ProductImageDTO> images = productImageRepository
                .findProductImageByProductId(product.getId())
                .map(productImageMapper::getImagePath)
                .stream().toList();
        ProductDTO item = new ProductDTO();
        item.setId(product.getId());
        item.setName(product.getName());
        item.setBaseprice(product.getBasePrice());
        item.setDiscount(product.getDiscount());
        item.setShipping(product.getShipping());
        item.setTotal(product.getTotal());
        item.setTax(product.getTax());
        item.setProfit(product.getProfit());
        item.setImages(images);
        return item;
    }
}
