package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.models.SignupRequests;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import com.example.madrasdaapi.services.AdminServices.AdminService;
import com.example.madrasdaapi.services.AdminServices.MockupService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import com.example.madrasdaapi.services.commons.PaymentService;
import com.example.madrasdaapi.services.commons.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@Tag(name = "Admin Resource Controller")

@CrossOrigin
@RequiredArgsConstructor
public class AdminController {
     private final VendorService vendorService;
     private final AdminService adminService;
     private final MockupService mockupService;
     private final PaymentService paymentService;
     private final TransactionService transactionService;
     private final UserRepository userRepository;
     private final VendorRepository vendorRepository;

     //     @PreAuthorize("hasRole('ADMIN')")
     @GetMapping("getVendors")
     public List<VendorMenuItemDTO> getVendorList() {

          return vendorService.getVendors();
     }

     @PostMapping("addVendor")
     public VendorDTO addVendor(@RequestBody RegisterDTO vendorDTO) {
          if (userRepository.existsByEmail(vendorDTO.getEmail())) throw new RuntimeException("Vendor already exists");
          return adminService.saveOrUpdateVendor(vendorDTO);
     }

     @DeleteMapping("deleteVendor/{id}")
     public void deleteVendor(@PathVariable Long id) {
          if (!vendorRepository.existsById(id)) throw new ResourceNotFoundException("Vendor", "id", id.toString());
          adminService.deleteVendor(id);
     }

     @GetMapping("getPayoutRequestedVendors")
     public List<VendorMenuItemDTO> getVendorPayouts() {
          return adminService.getPayoutRequests();
     }

     @PostMapping("completePayout/{id}")
     public void togglePayout(@PathVariable Long id) {
          paymentService.togglePayout(id);
     }

     @DeleteMapping("cancelOrder/{transactionId}")
     public void cancelOrder(@PathVariable Long transactionId) throws IOException {
          transactionService.resolveCancelOrder(transactionId);

     }
}

