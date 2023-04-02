package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.commons.ProductImageDTO;
import com.example.madrasdaapi.models.ProductImage;
import com.example.madrasdaapi.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductImageMapper {
    private final ProductImageRepository productImageRepository;
    public ProductImageDTO getImagePath(ProductImage productImage){
        ProductImageDTO item = new ProductImageDTO();
        item.setImagePath(productImage.getImgUrl());
        return item;
    }
}
