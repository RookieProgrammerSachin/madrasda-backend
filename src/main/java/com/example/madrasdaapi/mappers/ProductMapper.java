package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.commons.*;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.models.ProductImage;
import com.example.madrasdaapi.models.ProductSKUMapping;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMapper {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;
    private final ModelMapper mapper;
    private final VendorRepository vendorRepository;
    private final ProductSKUMappingRepository productSKUMappingRepository;
    private final ColorRepository colorRepository;

    public ProductDTO mapToDTO(Product product) {

        ProductDTO productDTO = mapper.map(product, ProductDTO.class);

        HashMap<String, ColorDTO> colors = new HashMap<>();
        for (ProductSKUMapping sku : product.getSkuMappings()) {
            String color = sku.getColor().getColor();
            ColorDTO colorDTO = colors.getOrDefault(color, new ColorDTO());
            SizeDTO sizeDTO = mapper.map(sku.getSize(), SizeDTO.class);

            sizeDTO.setSku(sku.getSku());
            mapper.map(sku.getColor(), colorDTO);

            colorDTO.getSizes().add(sizeDTO);
            colors.put(color, colorDTO);

        }

        for (ProductImage image : product.getProductImages()) {
            colors.get(image.getColor().getColor()).getImages().add(image.getImgUrl());
        }
        productDTO.setColors(new ArrayList<>(colors.values()));
        return productDTO;
    }

    public Product mapToEntity(NewProductDTO detachedProduct) {
        mapper.getConfiguration()
                .setSkipNullEnabled(true);
        Product product = new Product();
        if (detachedProduct.getId() != null)
            product = productRepository.findById(detachedProduct.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", detachedProduct.getId().toString()));

        Vendor vendor = vendorRepository.findByUser_Email(AuthContext.getCurrentUser());
        List<ProductSKUMapping> skus = productSKUMappingRepository.findByMockup_IdAndColor_IdIn(
                detachedProduct.getMockupId(),
                detachedProduct.getColors()
        );
        mapper.map(detachedProduct, product);
        product.setVendor(vendor);
        product.setSkuMappings(skus);
        product.setProductImages(new ArrayList<>());
        for (ProductImageDTO img : detachedProduct.getProductImages()) {
            ProductImage productImage = new ProductImage();
            productImage.setId(img.getId());
            productImage.setProduct(product);
            productImage.setColor(colorRepository.findById(img.getColor())
                    .orElseThrow(() -> new ResourceNotFoundException("Color", "id", img.getColor().toString())));
            productImage.setImgUrl(img.getImageUrl());
            product.getProductImages().add(productImage);
        }

        return product;

    }

    public ProductSKUMappingDTO mapSKUToDTO(ProductSKUMapping productSKUMapping) {
        ProductSKUMappingDTO dto = new ProductSKUMappingDTO();
        dto.setId(productSKUMapping.getId());
        dto.setSku(productSKUMapping.getSku());
        dto.setSize(productSKUMapping.getSize());
        dto.setColor(productSKUMapping.getColor());
        dto.setMockup(mapper.map(productSKUMapping.getMockup(), MockupDTO.class));
        return dto;
    }
}
