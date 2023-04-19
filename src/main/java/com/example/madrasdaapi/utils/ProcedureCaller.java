package com.example.madrasdaapi.utils;

import com.example.madrasdaapi.dto.commons.ProductLadderItem;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProcedureCaller {
    private final VendorRepository vendorRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Transactional
    public List<ProductLadderItem> getTopSellingProductsForVendor(Long vendorId, Integer limit) {
        List<Object[]> results = vendorRepository.TOP_SELLERS_FOR_VENDOR(vendorId, limit);
        List<ProductLadderItem> products = new ArrayList<>();

        for (Object[] result : results) {
            Long id = (Long) result[0];
            BigDecimal totalSales = (BigDecimal) result[1];
            Product p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
            ProductLadderItem item = new ProductLadderItem();
            modelMapper.map(p, item);
            item.setProfitAmount(p.getProfit().multiply(totalSales));
            item.setStocksSold(totalSales.longValue());
            products.add(item);
        }
        return products;
    }
    public List<Double> getMonthlySalesByVendorId(Long vendorId) {
        Object[][] results = vendorRepository.monthly_sales_by_id(vendorId);
        if(results.length == 0)
            return null;
        List<Double> monthlySales = new ArrayList<>(0);
        for (Object result : results[0]) {
            monthlySales.add(((Double) result));
        }
        return monthlySales;
    }

    public Integer getProductsSoldToday(Long vendorId){
        return vendorRepository.products_sold_today(vendorId);
    }
}
