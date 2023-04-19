package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import com.example.madrasdaapi.services.commons.ProductService;
import com.example.madrasdaapi.utils.ProcedureCaller;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Customer Resource Controller")
@RequestMapping("/api/client")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {
    private final VendorService vendorService;
    private final ProductService productService;
    private final ProcedureCaller caller;

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
}
