package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorMapper {
     private final VendorRepository vendorRepository;
     private final UserRepository userRepository;


     public VendorMenuItemDTO mapToMenuItemDTO(Vendor vendor) {
          VendorMenuItemDTO item = new VendorMenuItemDTO();
          item.setId(vendor.getUser().getId());
          item.setName(vendor.getUser().getName());
          item.setImgUrl(vendor.getProfilePic());
          return item;
     }


     public VendorDTO mapToDTO(Vendor vendor) {
          VendorDTO vendorDTO = new VendorDTO();
          vendorDTO.setId(vendor.getId());
          vendorDTO.setName(vendor.getUser().getName());
          vendorDTO.setImgUrl(vendor.getProfilePic());
          return vendorDTO;
     }

     public Vendor mapToEntity(VendorDTO vendorDTO) {
          String email = SecurityContextHolder.getDeferredContext().get().getAuthentication().getName();
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
