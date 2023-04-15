package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
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
import org.springframework.security.core.context.SecurityContextHolder;
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
          Design detachedFrontDesign = designRepository.findById(templateDTO.getFrontDesign().getId()).get();
          Design detachedBackDesign = designRepository.findById(templateDTO.getBackDesign().getId()).get();
          Mockup detachedMockup = mockupRepository.findById(templateDTO.getMockup().getId()).get();
          Vendor detachedVendor = vendorRepository.findById(templateDTO.getVendorId()).get();
          detachedBackDesign.setVendor(detachedVendor);
          detachedFrontDesign.setVendor(detachedVendor);

          Template detachedTemplate = new Template();
          detachedTemplate.setId(templateDTO.getId());
          detachedTemplate.setFrontDesignImage(detachedFrontDesign.getImgUrl());
          detachedTemplate.setBackDesignImage(detachedBackDesign.getImgUrl());
          detachedTemplate.setMockup(detachedMockup);
          detachedTemplate.setVendor(detachedVendor);
          detachedTemplate.setFrontDesignPlacement(templateDTO.getFrontDesignPlacement());
          detachedTemplate.setBackDesignPlacement(templateDTO.getBackDesignPlacement());

          Template template = templateRepository.save(detachedTemplate);
          return templateMapper.mapToTemplateDTO(template);
     }

     public TemplateDTO retrieveTemplate(Long templateId) {
          Long vendor = vendorRepository.findIdByUser_Email(SecurityContextHolder.getDeferredContext()
                  .get().getAuthentication().getName());
          return templateMapper.mapToTemplateDTO(templateRepository.findByIdAndVendor_Id(templateId, vendor)
                  .orElseThrow(() -> new ResourceNotFoundException("Template", "id", templateId.toString())));
     }

     public void deleteTemplate(Long id) {
          templateRepository.deleteById(id);
     }
}
//https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQ7IryN086-2xO9kANHTceq_lWWljufT0K4z26rPWd-fK_gmy25