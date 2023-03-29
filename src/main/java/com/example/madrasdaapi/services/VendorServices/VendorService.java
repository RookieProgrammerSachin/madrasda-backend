package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.ProductLadderItem;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {
     private final VendorRepository vendorRepository;
     private final DesignService designService;
     private final FeedbackService feedbackService;

     public VendorService(VendorRepository vendorRepository, DesignService designService, FeedbackService feedbackService) {
          this.vendorRepository = vendorRepository;
          this.designService = designService;
          this.feedbackService = feedbackService;
     }

     public VendorMenuItemDTO mapToMenuItemDTO(Vendor vendor) {
          VendorMenuItemDTO item = new VendorMenuItemDTO();
          item.setId(vendor.getUser().getId());
          item.setName(vendor.getUser().getName());
          item.setImgUrl(vendor.getProfilePic());
          return item;
     }

     public VendorDTO getVendorById(Long id) {
          Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor does not exist"));
          return mapToDTO(vendor);
     }

     public VendorDTO mapToDTO(Vendor vendor) {
          VendorDTO vendorDTO = new VendorDTO();
          vendorDTO.setId(vendor.getId());
          vendorDTO.setName(vendor.getUser().getName());
          vendorDTO.setDesigns(vendor.getDesigns()
                  .stream()
                  .map(designService::mapToDTO)
                  .collect(Collectors.toSet()));
          vendorDTO.setFeedbacks(vendor.getFeedbacks()
                  .stream()
                  .map(feedbackService::mapToDTO)
                  .collect(Collectors.toSet()));
          vendorDTO.setImgUrl(vendor.getProfilePic());
          vendorDTO.setSalesAnalysis(new SalesAnalysis());
          vendorDTO.setProductLadder(List.of(new ProductLadderItem(), new ProductLadderItem(), new ProductLadderItem()));
          return vendorDTO;
     }

     public List<VendorMenuItemDTO> getVendors() {
          return vendorRepository.findAll().stream()
                  .map(this::mapToMenuItemDTO)
                  .collect(Collectors.toList());
     }
}
