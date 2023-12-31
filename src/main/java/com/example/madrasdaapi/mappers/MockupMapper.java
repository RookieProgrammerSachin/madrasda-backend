package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupImageDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupSkuDTO;
import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.models.MockupImage;
import com.example.madrasdaapi.models.ProductSKUMapping;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MockupMapper {
    private final ModelMapper mapper;


    public MockupDTO mapToDTO(Mockup mockup) {

        MockupDTO mockupDTO = mapper.map(mockup, MockupDTO.class);
        List<MockupImageDTO> imagesDTO = new ArrayList<>();
        List<MockupImage> images = mockup.getImages();
        for (MockupImage image : images) {
            MockupImageDTO imageDTO = new MockupImageDTO();
            imageDTO.setImage(image.getImage());
            imageDTO.setColorId(image.getColor().getId());

            imagesDTO.add(imageDTO);
        }
        mockupDTO.setImages(imagesDTO);
        return mockupDTO;
    }

    public Mockup mapToEntity(MockupDTO mockupDTO) {

        Mockup mockup = mapper.map(mockupDTO, Mockup.class);
        List<ProductSKUMapping> skus = new ArrayList<>();
        for (MockupSkuDTO sku : mockupDTO.getSkuMapping()) {
            ProductSKUMapping mockupSku = new ProductSKUMapping();
            mockupSku.setId(sku.getId());
            mockupSku.setSku(sku.getSku());
            mockupSku.setSize(sku.getSize());
            mockupSku.setColor(sku.getColor());
            mockupSku.setMockup(mockup);
            mockupSku.setStatus(true);
            skus.add(mockupSku);
        }
        mockup.setSkuMapping(skus);
        mockup.setDisabled(mockupDTO.getDisabled());
        return mockup;
    }

}
