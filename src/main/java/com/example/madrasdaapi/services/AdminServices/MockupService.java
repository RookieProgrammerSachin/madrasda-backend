package com.example.madrasdaapi.services.AdminServices;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupSkuDTO;
import com.example.madrasdaapi.mappers.MockupMapper;
import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.models.ProductSKUMapping;
import com.example.madrasdaapi.models.Size;
import com.example.madrasdaapi.repositories.ColorRepository;
import com.example.madrasdaapi.repositories.MockupRepository;
import com.example.madrasdaapi.repositories.ProductSKUMappingRepository;
import com.example.madrasdaapi.repositories.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service

@RequiredArgsConstructor
public class MockupService {
     private final MockupMapper mockupMapper;
     private final MockupRepository mockupRepository;
     private final ColorRepository colorRepository;
     private final SizeRepository sizeRepository;
     private final ProductSKUMappingRepository productSKUMappingRepository;
     private final ModelMapper mapper;


     public MockupDTO addMockup(@RequestBody MockupDTO mockupDTO) {
          List<MockupSkuDTO> skuDTOS = new ArrayList<>();
          for(MockupSkuDTO skuMapping : mockupDTO.getSkuMapping()) {
               Color color = colorRepository.findById(skuMapping.getColor().getId()).orElseThrow();
               Size size = sizeRepository.findById(skuMapping.getSize().getId()).orElseThrow();
               skuMapping.setColor(color);
               skuMapping.setSize(size);
               skuDTOS.add(skuMapping);
          }
          mockupDTO.setSkuMapping(skuDTOS);
          Mockup detachedMockup = mockupMapper.mapToEntity(mockupDTO);
          return mockupMapper.mapToDTO(mockupRepository.save(detachedMockup));
     }

     /*public MockupDTO updateMockup(MockupDTO mockupDTO) {
          Mockup detachedMockup = mockupRepository.findById(mockupDTO.getId()).get();
          mapper.getConfiguration().setSkipNullEnabled(true);
          mapper.map(mockupDTO, detachedMockup);
          if(mockupDTO.getColors() != null) detachedMockup.setColors(mockupDTO.getColors());
          if(mockupDTO.getSizes() != null) detachedMockup.setSizes(mockupDTO.getSizes());
          return mockupMapper.mapToDTO(mockupRepository.saveOrUpdate(detachedMockup));
     }*/

     public void deleteMockup(Long id) {
          Mockup detachedMockup = mockupRepository.findById(id).orElseThrow();
          for(ProductSKUMapping sku : detachedMockup.getSkuMapping()){
               productSKUMappingRepository.deleteById(sku.getId());
          }
          mockupRepository.deleteById(id);
     }

     public MockupDTO getMockupById(Long id) {
          return mockupMapper.mapToDTO(mockupRepository.findById(id).get());
     }

     public Page<MockupDTO> getAllMockups(int pageNo, int pageSize) {
          return mockupRepository.findAll(PageRequest.of(pageNo, pageSize)).map(mockupMapper::mapToDTO);
     }
}
