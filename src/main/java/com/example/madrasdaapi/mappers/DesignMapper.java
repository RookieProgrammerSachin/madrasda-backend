package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.VendorDTO.DesignDTO;
import com.example.madrasdaapi.models.Design;
import org.springframework.stereotype.Service;

@Service
public class DesignMapper {
     public DesignDTO mapToDTO(Design design) {
          DesignDTO designDTO = new DesignDTO();
          designDTO.setId(design.getId());
          designDTO.setPosition(design.getPosition());
          designDTO.setImg(design.getImg());
          designDTO.setTheme(design.getTheme());
          designDTO.setPrintType(design.getPrintType());
          return designDTO;
     }

     public Design mapToEntity(DesignDTO designDTO) {
          Design design = new Design();
          design.setId(designDTO.getId());
          design.setPosition(designDTO.getPosition());
          design.setImg(designDTO.getImg());
          design.setTheme(designDTO.getTheme());
          design.setPrintType(designDTO.getPrintType());
          return design;
     }
}
