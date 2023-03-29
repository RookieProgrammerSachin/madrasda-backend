package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor/")
@CrossOrigin
public class VendorController {
     private final VendorService vendorService;

     public VendorController(VendorService vendorService) {
          this.vendorService = vendorService;
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
