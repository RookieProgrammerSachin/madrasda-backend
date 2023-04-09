package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.models.Mockup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockupMapper {
    private final ModelMapper mapper;


    public MockupDTO mapToDTO(Mockup mockup) {

        MockupDTO mockupDTO = mapper.map(mockup, MockupDTO.class);

        return mockupDTO;
    }

    public Mockup mapToEntity(MockupDTO mockupDTO) {
        return mapper.map(mockupDTO, Mockup.class);
    }
}
