package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.mappers.DesignMapper;
import com.example.madrasdaapi.mappers.FeedbackMapper;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {
     private final VendorRepository vendorRepository;
     private final VendorMapper  vendorMapper;
     public VendorService(VendorRepository vendorRepository, VendorMapper vendorMapper) {
          this.vendorRepository = vendorRepository;
          this.vendorMapper = vendorMapper;
     }

     public VendorDTO getVendorById(Long id) {
          Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor does not exist"));
          return vendorMapper.mapToVendorDetails(vendor);
     }
     public List<VendorMenuItemDTO> getVendors() {
          return vendorRepository.findAll().stream()
                  .map(vendorMapper::mapToMenuItemDTO)
                  .collect(Collectors.toList());
     }

}
