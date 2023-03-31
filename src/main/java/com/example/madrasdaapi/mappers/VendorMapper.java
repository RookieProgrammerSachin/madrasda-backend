package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.commons.ProductLadderItem;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDetails;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorMapper {
     private final VendorRepository vendorRepository;
     private final ModelMapper mapper;


     public VendorMenuItemDTO mapToMenuItemDTO(Vendor vendor) {
          VendorMenuItemDTO item = new VendorMenuItemDTO();
          item.setId(vendor.getUser().getId());
          item.setName(vendor.getUser().getName());
          item.setImgUrl(vendor.getProfilePic());
          return item;
     }


     public VendorDetails mapToVendorDetails(Vendor vendor) {
          VendorDetails vendorDetails = new VendorDetails();
          VendorDTO vendorDTO = mapToDTO(vendor);
          vendorDetails.setVendorDTO(vendorDTO);
          vendorDetails.setSalesAnalysis(new SalesAnalysis());
          vendorDetails.setProductLadder(List.of(new ProductLadderItem(), new ProductLadderItem(), new ProductLadderItem()));
          return vendorDetails;
     }

     public VendorDTO mapToDTO(Vendor vendor) {
          VendorDTO vendorDTO = new VendorDTO();
          vendorDTO.setId(vendor.getId());
          vendorDTO.setName(vendor.getUser().getName());
          vendorDTO.setImgUrl(vendor.getProfilePic());
          return vendorDTO;
     }
     public Vendor mapToEntity(VendorDTO vendorDTO){
          Vendor vendor = vendorRepository.findById(vendorDTO.getId()).orElseThrow(() -> new RuntimeException("Vendor does not exist"));
          vendor.getUser().setName(vendorDTO.getName());
          vendor.setProfilePic(vendorDTO.getImgUrl());
          return vendor;

     }



}
