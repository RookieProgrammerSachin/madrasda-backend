package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorMapper {
     private final VendorRepository vendorRepository;
     private final UserRepository userRepository;
     private final ModelMapper mapper;

     public VendorMenuItemDTO mapToMenuItemDTO(Vendor vendor) {
          VendorMenuItemDTO item = new VendorMenuItemDTO();
          item.setId(vendor.getUser().getId());
          item.setName(vendor.getUser().getName());
          item.setCompanyName(vendor.getCompanyName());
          item.setImgUrl(vendor.getProfilePic());
          return item;
     }
     public VendorMenuItemDTO mapToMenuItemDTOWithPayout(Vendor vendor) {
          VendorMenuItemDTO vendorMenuItemDTO = mapToMenuItemDTO(vendor);
          vendorMenuItemDTO.setPayoutRequested(vendor.getPayoutRequested());
          vendorMenuItemDTO.setPayoutAmount(vendor.getOutstandingProfit());
          return vendorMenuItemDTO;
     }


     public VendorDTO mapToDTO(Vendor vendor) {
          VendorDTO vendorDTO = new VendorDTO();
          vendorDTO.setId(vendor.getId());
          vendorDTO.setName(vendor.getUser().getName());
          vendorDTO.setEmail(vendor.getUser().getEmail());
          vendorDTO.setImgUrl(vendor.getProfilePic());
          vendorDTO.setCompanyName(vendor.getCompanyName());
          vendorDTO.setCompanyUrl(vendor.getCompanyUrl());
          vendorDTO.setGSTIN(vendor.getGSTIN());
          vendorDTO.setStatus(vendor.getStatus());
          return vendorDTO;
     }

     public Vendor mapToEntity(VendorDTO vendorDTO) {
          String email = AuthContext.getCurrentUser();
          Vendor vendor = vendorRepository.findByUser_Email(email);

          if (vendorDTO.getName() != null) {
               vendor.getUser().setName(vendorDTO.getName());
          }
          if (vendorDTO.getEmail() != null) {
               vendor.getUser().setEmail(vendorDTO.getEmail());
          }
          if (vendorDTO.getPhone() != null) {
               vendor.getUser().setPhone(vendorDTO.getPhone());
          }
          if (vendorDTO.getImgUrl() != null) {
               vendor.setProfilePic(vendorDTO.getImgUrl());
          }

          return vendor;
     }


}
