package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.AuthDTO.LoginDTO;
import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.dto.commons.UserDTO;
import com.example.madrasdaapi.services.AdminServices.AdminService;
import com.example.madrasdaapi.services.CustomerServices.CustomerService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import com.example.madrasdaapi.services.commons.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Customer Resource Controller")
@RequestMapping("/api/client")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {
    private final VendorService vendorService;
    private final AdminService adminService;
    private final ProductService productService;
    private final CustomerService customerService;

    @GetMapping("/allProducts")
    public Page<ProductDTO> getAllProducts(
            @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
            @RequestParam(defaultValue = "10", name = "pageSize") int pageSize
    ) {
        return productService.getAllProducts(pageNo, pageSize);
    }

    @GetMapping("getProductsByVendor/{vendorId}")
    public Page<ProductDTO> getProductsByVendor(@PathVariable Long vendorId,
                                                @RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize) {
        return productService.getProductsByVendor(vendorId, pageNo, pageSize);
    }
    @GetMapping("products/{audience}")
    public Page<ProductDTO> getProductsByCategory(
            @PathVariable String audience,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return productService.getByAudience(pageNo, pageSize, audience);
    }

    @GetMapping("searchProduct/{searchTerm}")
    public Page<ProductDTO> searchProduct(
            @PathVariable String searchTerm,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        return productService.searchProducts(pageNo, pageSize, searchTerm);
    }

    @GetMapping("getAllVendors")
    public List<VendorMenuItemDTO> getAllVendors() {
        return vendorService.getAllEnabledVendors();
    }
    @PutMapping("updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody LoginDTO request){
        adminService.updatePassword(request.getPassword());
        return ResponseEntity.ok("Updated Password");
    }
    @PutMapping("updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody RegisterDTO request){
        customerService.updateProfile(request);
        return ResponseEntity.ok("Updated Profile");
    }
    @GetMapping("myProfile")
    public UserDTO getProfile(){
        return customerService.getUserProfile();
    }
}
