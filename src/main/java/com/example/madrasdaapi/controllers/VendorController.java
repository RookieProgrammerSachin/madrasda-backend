package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.VendorDTO.*;
import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.services.AdminServices.AdminService;
import com.example.madrasdaapi.services.AdminServices.MockupService;
import com.example.madrasdaapi.services.VendorServices.DesignService;
import com.example.madrasdaapi.services.VendorServices.TemplateService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Vendor Resource Controller")
public class VendorController {
     private final VendorService vendorService;
     private final AdminService adminService;
     private final MockupService mockupService;
     private final TemplateService templateService;
     private final DesignService designService;
     @GetMapping
     public VendorDetails getVendorDetails() {
          String email = SecurityContextHolder.getDeferredContext().get().getAuthentication().getName();
          return vendorService.getVendorDetails(email);
     }


     @PutMapping("updateVendor")
     public VendorDTO saveOrUpdateVendor(@RequestBody VendorDTO registerDTO) {

          return adminService.updateVendor(registerDTO);
     }

     @GetMapping("getMockup/{id}")
     public MockupDTO retrieveMockup(@PathVariable Long id){
          return mockupService.getMockupById(id);
     }

     @GetMapping("getDesign/{id}")
     public DesignDTO getDesignById(@PathVariable Long id) {
          return designService.getDesignById(id);
     }

     @PostMapping("addDesign")
     public DesignDTO addDesign(@RequestBody DesignDTO designDTO) {
          return designService.save(designDTO);
     }
     @PutMapping("updateDesign")
     public DesignDTO updateDesign(@RequestBody DesignDTO designDTO) {
          return designService.update(designDTO);
     }


}
