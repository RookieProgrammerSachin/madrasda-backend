package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.commons.ColorDTO;
import com.example.madrasdaapi.dto.commons.NewProductDTO;
import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.dto.commons.SizeDTO;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.models.ProductImage;
import com.example.madrasdaapi.models.ProductSKUMapping;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.ProductImageRepository;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.ProductSKUMappingRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

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
        return productDTO;
    }

    public Product mapToEntity(NewProductDTO detachedProduct) {
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        Product product = productRepository.findById(detachedProduct.getId()).orElse(new Product());
        Vendor vendor = vendorRepository.findById(detachedProduct.getVendor().getId()).get();
        List<ProductSKUMapping> skus = productSKUMappingRepository.findByMockup_IdAndColor_IdIn(
                detachedProduct.getMockupId(),
                detachedProduct.getColors()
        );
        product.setMockup(skus.get(0).getMockup());
        mapper.createTypeMap(NewProductDTO.class, Product.class)
                .addMapping(NewProductDTO::getProductImages, Product::setProductImages)
                .map(detachedProduct, product);
        product.setVendor(vendor);
        product.setSkuMappings(skus);
        return product;

    }
}
