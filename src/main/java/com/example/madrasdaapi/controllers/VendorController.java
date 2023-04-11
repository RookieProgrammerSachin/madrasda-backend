package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.VendorDTO.DesignDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDetails;
import com.example.madrasdaapi.services.AdminServices.AdminService;
import com.example.madrasdaapi.services.AdminServices.MockupService;
import com.example.madrasdaapi.services.VendorServices.DesignService;
import com.example.madrasdaapi.services.VendorServices.TemplateService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import com.example.madrasdaapi.services.commons.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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
     private final PaymentService paymentService;
     @GetMapping
     public VendorDetails getVendorDetails() throws SQLException {
          String email = SecurityContextHolder.getDeferredContext().get().getAuthentication().getName();
          return vendorService.getVendorDetails(email);
     }


     @PutMapping("updateVendor")
     public VendorDTO saveOrUpdateVendor(@RequestBody VendorDTO registerDTO) {
          return adminService.updateVendor(registerDTO);
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

     @DeleteMapping("deleteDesign/{designId}")
     public void deleteDesignById(@PathVariable Long designId) {
          designService.deleteById(designId);
     }

     @Transactional
     @GetMapping("monthlySales/{vendorId}")
     public List<Double> getMonthlySales(@PathVariable Long vendorId) {
          return vendorService.getMonthlySalesByVendorId(vendorId);
     }

     @PostMapping("requestPayout")
     public void requestPayout() {
          String email = SecurityContextHolder.getDeferredContext().get().getAuthentication().getName();
          paymentService.requestPayout(email);

     }
     @GetMapping("vendorDetailsById/{id}")
     public VendorDetails viewDashboard(
             @PathVariable String id) {

          return vendorService.getVendorDetails(id);
     }

}
