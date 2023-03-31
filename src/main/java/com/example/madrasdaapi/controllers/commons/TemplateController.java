package com.example.madrasdaapi.controllers.commons;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.services.VendorServices.TemplateService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates/")
@RequiredArgsConstructor
public class TemplateController {
     private final TemplateService templateService;
     private final VendorService vendorService;

     @GetMapping("getVendorTemplates/{vendorId}")
     public Page<TemplateDTO> getTemplates(@PathVariable Long vendorId,
                                           @RequestParam(defaultValue = "0") int pageNo,
                                           @RequestParam(defaultValue = "10") int pageSize){
          return vendorService.retriveAllTemplates(vendorId,pageNo, pageSize);
     }

     @GetMapping("getTemplate/{templateId}")
     public TemplateDTO getTemplate(@PathVariable Long templateId){
          return templateService.retrieveTemplate(templateId);
     }

     @PostMapping("saveOrUpdateTemplate")
     public TemplateDTO createTemplate(@RequestBody TemplateDTO productTemplateDTO){
          return templateService.saveOrUpdateTemplate(productTemplateDTO);
     }
     @DeleteMapping("deleteTemplate/{id}")
     public void deleteTemplate(@PathVariable Long id){
          templateService.deleteTemplate(id);
     }
}