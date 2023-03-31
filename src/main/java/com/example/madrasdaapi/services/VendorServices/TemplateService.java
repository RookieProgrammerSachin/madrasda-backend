package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.mappers.DesignMapper;
import com.example.madrasdaapi.mappers.MockupMapper;
import com.example.madrasdaapi.mappers.TemplateMapper;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.Design;
import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.models.Template;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.DesignRepository;
import com.example.madrasdaapi.repositories.MockupRepository;
import com.example.madrasdaapi.repositories.TemplateRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {
     private final DesignMapper designMapper;
     private final MockupMapper mockupMapper;
     private final TemplateMapper templateMapper;
     private final VendorMapper vendorMapper;
     private final MockupRepository mockupRepository;
     private final DesignRepository designRepository;
     private final VendorRepository vendorRepository;
     private final TemplateRepository templateRepository;


     public TemplateDTO saveOrUpdateTemplate(TemplateDTO templateDTO) {
          Design detachedDesign = designMapper.mapToEntity(templateDTO.getDesign());
          Mockup detachedMockup = mockupMapper.mapToEntity(templateDTO.getMockup());
          Vendor detachedVendor = vendorMapper.mapToEntity(templateDTO.getVendor());
          Template detachedTemplate = new Template();
          detachedTemplate.setId(templateDTO.getId());
          detachedTemplate.setDesign(detachedDesign);
          detachedTemplate.setMockup(detachedMockup);
          detachedTemplate.setVendor(detachedVendor);
          Template template = templateRepository.save(detachedTemplate);
          return templateMapper.mapToTemplateDTO(template);
     }

     public TemplateDTO retrieveTemplate(Long templateId) {
          return templateMapper.mapToTemplateDTO(templateRepository.findById(templateId).get());
     }

     public void deleteTemplate(Long id) {
          templateRepository.deleteById(id);
     }
}
