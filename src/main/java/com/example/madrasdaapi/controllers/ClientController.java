package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.dto.commons.ProductLadderItem;
import com.example.madrasdaapi.services.CustomerServices.CustomerService;
import com.example.madrasdaapi.services.VendorServices.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Customer Resource Controller")
@RequestMapping("/api/client/")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {
    private final CustomerService customerService;
    private final VendorService vendorService;
    @GetMapping("test")
    public String hello(){
        return "This is working";
    }
    @GetMapping("/products")
    public List<ProductDTO> getAllProducts(){
        return customerService.getAllProducts();
    }
    @GetMapping("/vendorproducts")
    @Transactional
    public List<List<ProductLadderItem>> getProductsForEachVendor() {
        List<List<ProductLadderItem>> items = new ArrayList<>();
        items.add(vendorService.getTopSellingProductsForVendor(1L));
        items.add(vendorService.getTopSellingProductsForVendor(4L));
        items.add(vendorService.getTopSellingProductsForVendor(2L));
        items.add(vendorService.getTopSellingProductsForVendor(3L));
        return items;
    }
}
