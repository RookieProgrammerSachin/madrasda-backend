package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@Tag(name = "Admin Resource Controller")
@CrossOrigin
@RequiredArgsConstructor

public class AdminController {
     private final VendorService vendorService;

     @GetMapping("getVendors")
     public List<VendorMenuItemDTO> getVendorList() {
          return vendorService.getVendors();
     }

//     @GetMapping("addVendor")
//     public VendorDTO admin(){
//
//     }

}

