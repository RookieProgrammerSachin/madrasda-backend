package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.repositories.VendorRepository;
import com.example.madrasdaapi.services.VendorServices.TemplateService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates/")
@RequiredArgsConstructor
@CrossOrigin
public class TemplateController {
     private final TemplateService templateService;
     private final VendorService vendorService;
     private final VendorRepository vendorRepository;

     @GetMapping("getTemplates")
     public Page<TemplateDTO> getTemplates(@RequestParam(defaultValue = "0") int pageNo,
                                           @RequestParam(defaultValue = "10") int pageSize){
          Long vendor = vendorRepository.findIdByUser_Email(SecurityContextHolder.getDeferredContext()
                  .get().getAuthentication().getName());
          return vendorService.retrieveAllTemplates(vendor,pageNo, pageSize);
     }

     @GetMapping("getTemplate/{templateId}")
     public TemplateDTO getTemplate(@PathVariable Long templateId){
          return templateService.retrieveTemplate(templateId);
     }

     @PostMapping("saveTemplate")
     public TemplateDTO saveTemplate(@RequestBody TemplateDTO productTemplateDTO){
          return templateService.saveOrUpdateTemplate(productTemplateDTO);
     }

     @DeleteMapping("deleteTemplate/{id}")
     public void deleteTemplate(@PathVariable Long id){
          templateService.deleteTemplate(id);
     }

}
