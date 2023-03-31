package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDetails;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.mappers.TemplateMapper;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.Template;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.TemplateRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorService {
     private final VendorMapper vendorMapper;
     private final TemplateMapper templateMapper;
     private final VendorRepository vendorRepository;
     private final TemplateRepository templateRepository;


     public VendorDetails getVendorById(Long id) {
          Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor does not exist"));
          return vendorMapper.mapToVendorDetails(vendor);
     }

     public List<VendorMenuItemDTO> getVendors() {
          return vendorRepository.findAll().stream()
                  .map(vendorMapper::mapToMenuItemDTO)
                  .collect(Collectors.toList());
     }

     public Page<TemplateDTO> retriveAllTemplates(Long vendorId, int pageNo, int pageSize) {
          PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
          Page<Template> page = templateRepository.findByVendor_Id(vendorId, pageRequest);
          return page.map(templateMapper::mapToTemplateDTO);
     }

}
