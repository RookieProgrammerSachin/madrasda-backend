package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.dto.commons.ProductLadderItem;
import com.example.madrasdaapi.services.CustomerServices.CustomerService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Customer Resource Controller")
@RequestMapping("/api/client")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {
    private final CustomerService customerService;
    private final VendorService vendorService;

    @GetMapping("/allProducts")
    public Page<ProductDTO> getAllProducts(
            @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
            @RequestParam(defaultValue = "10", name = "pageSize") int pageSize
    ){
        return customerService.getAllProducts(pageNo, pageSize);
    }
//    @GetMapping("/vendorProducts")
//    @Transactional
//    public List<List<ProductLadderItem>> getProductsForEachVendor(
//    ) {
//        return customerService.getAllVendorId().stream()
//                .map(vendorService::getTopSellingProductsForVendor).toList();
//    }
    @GetMapping("/topSellingVendorProducts/{id}")
    public Page<ProductLadderItem> getProductsForVendor(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
            @RequestParam(defaultValue = "10", name = "pageSize") int pageSize
    ) {
        List<ProductLadderItem> items = vendorService.getTopSellingProductsForVendor(id);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), items.size());
        return new PageImpl<>(items.subList(start, end), pageable, items.size());
    }
//    @GetMapping("/products/{category}")
//    public List<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
//        return customerService.getProductsByCategory(category);
//    }
}
