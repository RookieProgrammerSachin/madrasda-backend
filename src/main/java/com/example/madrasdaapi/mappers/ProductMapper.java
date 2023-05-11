package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.dto.commons.*;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.models.*;
import com.example.madrasdaapi.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.*;

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
        productDTO.setCategory(product.getMockup().getName());
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
        Mockup mockup = skus.get(0).getMockup();
        product.setVendor(vendor);
        product.setSkuMappings(skus);
        product.setProductImages(new ArrayList<>());
        product.setHeight(mockup.getHeight());
        product.setBreadth(mockup.getBreadth());
        product.setLength(mockup.getLength());
        product.setWeight(mockup.getWeight());
        product.setHsn(mockup.getHsn());
        product.setTax(mockup.getTax());
        product.setBasePrice(mockup.getBasePrice());
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

    public NewProductDTO mapToNewProductDTO(Product product) {
        NewProductDTO dto = new NewProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setAudience(product.getAudience());
        dto.setDescription(product.getDescription());
        dto.setBasePrice(product.getBasePrice().floatValue());
        dto.setDiscount(product.getDiscount().floatValue());
        dto.setTotal(product.getTotal().floatValue());
        dto.setProfit(product.getProfit().floatValue());
        dto.setTax(product.getTax().floatValue());
        dto.setPublishStatus(product.getPublishStatus());
        VendorMenuItemDTO vendorDTO = new VendorMenuItemDTO();
        vendorDTO.setId(product.getVendor().getId());
        dto.setVendor(vendorDTO);
        dto.setFrontDesignUrl(product.getFrontDesignUrl());
        dto.setFrontDesignPlacement(product.getFrontDesignPlacement());
        dto.setBackDesignUrl(product.getBackDesignUrl());
        dto.setBackDesignPlacement(product.getBackDesignPlacement());
        dto.setMockupId(product.getMockup().getId());
        Set<Long> colorIds = new HashSet<>();
        for (ProductSKUMapping sku : product.getSkuMappings()) {
            colorIds.add(sku.getColor().getId());
        }
        dto.setColors(colorIds.stream().toList());
        List<ProductImageDTO> images = new ArrayList<>();
        for (ProductImage image : product.getProductImages()) {
            ProductImageDTO imageDTO = new ProductImageDTO();
            imageDTO.setId(image.getId());
            imageDTO.setColor(image.getColor().getId());
            imageDTO.setImageUrl(image.getImgUrl());
            images.add(imageDTO);
        }
        dto.setProductImages(images);
        return dto;

    }
}
