package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.VendorDTO.VendorDetails;

import com.example.madrasdaapi.services.VendorServices.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor/")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Vendor Resource Controller")
public class VendorController {
     private final VendorService vendorService;


     @GetMapping("{id}")
     public VendorDetails getVendorDetails(@PathVariable Long id) {
          return vendorService.getVendorById(id);
     }




}
