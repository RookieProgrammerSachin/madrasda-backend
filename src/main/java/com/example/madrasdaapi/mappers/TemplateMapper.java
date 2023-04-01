package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.models.Template;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateMapper {
     private final ModelMapper modelMapper;
     private final DesignMapper designMapper;

     public TemplateDTO mapToTemplateDTO(Template template){
          return modelMapper.map(template, TemplateDTO.class);
     }
}
