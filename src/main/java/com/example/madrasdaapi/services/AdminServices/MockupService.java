package com.example.madrasdaapi.services.AdminServices;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupImageDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupSkuDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.mappers.MockupMapper;
import com.example.madrasdaapi.models.*;
import com.example.madrasdaapi.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service

@RequiredArgsConstructor
public class MockupService {
     private final MockupMapper mockupMapper;
     private final MockupRepository mockupRepository;
     private final ColorRepository colorRepository;
     private final SizeRepository sizeRepository;
     private final ProductSKUMappingRepository productSKUMappingRepository;
     private final MockupImageRepository mockupImageRepository;
     private final ModelMapper mapper;


     public MockupDTO addMockup(@RequestBody MockupDTO mockupDTO) {
          List<MockupSkuDTO> skuDTOS = new ArrayList<>();
          HashMap<Long, Color> colors = new HashMap<>();
          for(MockupSkuDTO skuMapping : mockupDTO.getSkuMapping()) {
               Color color = colorRepository.findById(skuMapping.getColor().getId()).orElseThrow();
               colors.put(color.getId(), color);
               Size size = sizeRepository.findById(skuMapping.getSize().getId()).orElseThrow();
               skuMapping.setColor(color);
               skuMapping.setSize(size);
               skuDTOS.add(skuMapping);
          }
          mockupDTO.setSkuMapping(skuDTOS);
          Mockup detachedMockup = mockupMapper.mapToEntity(mockupDTO);
          List<MockupImage> imageList = new ArrayList<>();
          for (MockupImageDTO imageDTO : mockupDTO.getImages()) {
               MockupImage image = new MockupImage();
               image.setImage(imageDTO.getImage());
               image.setColor(colorRepository.findById(imageDTO.getColorId()).get());
               image.setMockup(detachedMockup);
               imageList.add(image);
          }
          detachedMockup.setImages(imageList);
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
          return mockupRepository.findByDisabled(false, PageRequest.of(pageNo, pageSize)).map(mockupMapper::mapToDTO);
     }

     public void disableMockup(Long id) {
          int n = mockupRepository.updateDisabledById(id);
          if(n != 1) throw new APIException("Mockup not found", HttpStatus.NOT_FOUND);

     }
}
