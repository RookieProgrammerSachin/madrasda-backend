package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.services.CustomerServices.CustomerService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import com.example.madrasdaapi.services.commons.ProductService;
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
    private final CustomerService customerService;
    private final VendorService vendorService;
    private final ProductService productService;

    @GetMapping("/allProducts")
    public Page<ProductDTO> getAllProducts(
            @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
            @RequestParam(defaultValue = "10", name = "pageSize") int pageSize
    ) {
        return customerService.getAllProducts(pageNo, pageSize);
    }

    //    @GetMapping("/vendorProducts")
//    @Transactional
//    public List<List<ProductLadderItem>> getProductsForEachVendor(
//    ) {
//        return customerService.getAllVendorId().stream()
//                .map(vendorService::getTopSellingProductsForVendor).toList();
//    }
    @GetMapping("getProductsByVendor/{vendorId}")
    public Page<ProductDTO> getProductsByVendor(@PathVariable Long vendorId,
                                                @RequestParam Integer pageNo,
                                                @RequestParam Integer pageSize) {
        return productService.getProductsByVendor(vendorId, pageNo, pageSize);
    }
//    @GetMapping("/products/{category}")
//    public List<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
//        return customerService.getProductsByCategory(category);
//    }
}
