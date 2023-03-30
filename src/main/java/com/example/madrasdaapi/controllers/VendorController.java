package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.dto.commons.FeedbackDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import com.example.madrasdaapi.services.commons.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor/")
@CrossOrigin
@Tag(name = "Vendor Resource Controller")
public class VendorController {
     private final VendorService vendorService;
     private final FeedbackService feedbackService;

     public VendorController(VendorService vendorService, FeedbackService feedbackService) {
          this.vendorService = vendorService;
          this.feedbackService = feedbackService;
     }

     @GetMapping("getVendors")
     public List<VendorMenuItemDTO> getVendorList(){
          return vendorService.getVendors();
     }
     @GetMapping("{id}")
     public VendorDTO getVendorDetails(@PathVariable Long id){
          return vendorService.getVendorById(id);
     }




}
