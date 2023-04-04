package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.DesignDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.mappers.DesignMapper;
import com.example.madrasdaapi.models.Design;
import com.example.madrasdaapi.repositories.DesignRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesignService {
     private final DesignRepository designRepository;
     private final DesignMapper designMapper;
     private final ModelMapper mapper;

     public DesignDTO getDesignById(Long id) {
          return designMapper.mapToDTO(designRepository.findById(id).get());
     }
     public DesignDTO update(DesignDTO designDTO){
          Design detachedDesign = designRepository.findById(designDTO.getId()).get();
          mapper.getConfiguration().setSkipNullEnabled(true);
          mapper.map(designDTO, detachedDesign);
          Design savedDesign = designRepository.save(detachedDesign);
          return designMapper.mapToDTO(savedDesign);
     }
     public DesignDTO save(DesignDTO designDTO){
          Design detachedDesign = designMapper.mapToEntity(designDTO);
          Design savedDesign = designRepository.save(detachedDesign);
          return designMapper.mapToDTO(savedDesign);
     }

     public void deleteById(Long designId) {
          designRepository.deleteById(designId);
     }
}
