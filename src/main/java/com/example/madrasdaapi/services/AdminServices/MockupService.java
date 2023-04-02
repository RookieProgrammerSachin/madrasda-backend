package com.example.madrasdaapi.services.AdminServices;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.mappers.MockupMapper;
import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.repositories.MockupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service

@RequiredArgsConstructor
public class MockupService {
     private final MockupMapper mockupMapper;
     private final MockupRepository mockupRepository;
     private final ModelMapper mapper;


     public MockupDTO addMockup(@RequestBody MockupDTO mockupDTO) {
          Mockup detachedMockup = mockupMapper.mapToEntity(mockupDTO);
          return mockupMapper.mapToDTO(mockupRepository.save(detachedMockup));
     }

     public MockupDTO updateMockup(MockupDTO mockupDTO) {
          Mockup detachedMockup = mockupRepository.findById(mockupDTO.getId()).get();
          mapper.getConfiguration().setSkipNullEnabled(true);
          mapper.map(mockupDTO, detachedMockup);
          if(mockupDTO.getColor() != null) detachedMockup.setColor(mockupDTO.getColor());
          if(mockupDTO.getSize() != null) detachedMockup.setSize(mockupDTO.getSize());
          return mockupMapper.mapToDTO(mockupRepository.save(detachedMockup));
     }

     public void deleteMockup(Long id) {
          mockupRepository.deleteById(id);
     }

     public MockupDTO getMockupById(Long id) {
          return mockupMapper.mapToDTO(mockupRepository.findById(id).get());
     }
}
