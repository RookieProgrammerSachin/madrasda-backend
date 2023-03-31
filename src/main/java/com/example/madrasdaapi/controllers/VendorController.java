package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDetails;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.services.VendorServices.TemplateService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import com.example.madrasdaapi.services.commons.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor/")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Vendor Resource Controller")
public class VendorController {
     private final VendorService vendorService;
     private final FeedbackService feedbackService;
     private final TemplateService templateService;


     @GetMapping("{id}")
     public VendorDetails getVendorDetails(@PathVariable Long id) {
          return vendorService.getVendorById(id);
     }




}
