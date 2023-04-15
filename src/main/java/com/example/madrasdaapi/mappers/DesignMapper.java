package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.VendorDTO.DesignDTO;
import com.example.madrasdaapi.models.Design;
import org.springframework.stereotype.Service;

@Service
public class DesignMapper {
     public DesignDTO mapToDTO(Design design) {
          DesignDTO designDTO = new DesignDTO();
          designDTO.setId(design.getId());
          designDTO.setDesignType(design.getDesignType());
          designDTO.setImgUrl(design.getImgUrl());
          designDTO.setTheme(design.getTheme());
          designDTO.setAdditionalInformation(design.getAdditionalInformation());

          return designDTO;
     }

     public Design mapToEntity(DesignDTO designDTO) {
          Design design = new Design();
          design.setId(designDTO.getId());
          design.setDesignType(designDTO.getDesignType());
          design.setImgUrl(designDTO.getImgUrl());
          design.setTheme(designDTO.getTheme());
          design.setAdditionalInformation(designDTO.getAdditionalInformation());
          return design;
     }
}
