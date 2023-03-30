package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.models.MockupDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MockupMapper {
     private final ModelMapper mapper;

     public MockupMapper(ModelMapper mapper) {
          this.mapper = mapper;
     }

     public MockupDTO mapToDTO(Mockup mockup) {
          return mapper.map(mockup, MockupDTO.class);
     }

     public Mockup mapToEntity(MockupDTO mockupDTO) {
          return mapper.map(mockupDTO, Mockup.class);
     }
}
