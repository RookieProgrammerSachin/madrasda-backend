package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupSkuDTO;
import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.models.ProductSKUMapping;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MockupMapper {
    private final ModelMapper mapper;


    public MockupDTO mapToDTO(Mockup mockup) {

        MockupDTO mockupDTO = mapper.map(mockup, MockupDTO.class);
        mockupDTO.setSkuMapping(mockup.getSkuMapping()
                .stream()
                .map(sku -> new MockupSkuDTO(sku.getId(),sku.getSku(), sku.getSize(), sku.getColor()))
                .collect(Collectors.toList()));
        return mockupDTO;
    }

    public Mockup mapToEntity(MockupDTO mockupDTO) {
        return mapper.map(mockupDTO, Mockup.class);
    }
}
