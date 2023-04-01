package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDetails;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.dto.commons.ProductLadderItem;
import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import com.example.madrasdaapi.mappers.TemplateMapper;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.models.Template;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.TemplateRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorService {
     private final VendorMapper vendorMapper;
     private final TemplateMapper templateMapper;
     private final ModelMapper modelMapper;
     private final VendorRepository vendorRepository;
     private final TemplateRepository templateRepository;
     private final ProductRepository productRepository;

     @Transactional
     public VendorDetails getVendorById(Long id) {
          Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor does not exist"));
          VendorDetails vendorDetails = new VendorDetails();
          VendorDTO vendorDTO = vendorMapper.mapToDTO(vendor);
          vendorDetails.setVendorDTO(vendorDTO);
          SalesAnalysis salesAnalysis = vendorRepository.getSalesAnalysisByVendorId(vendor.getId());
          vendorDetails.setSalesAnalysis(salesAnalysis);
          vendorDetails.setProductLadder(getTopSellingProductsForVendor(id));
          return vendorDetails;
     }

     public List<VendorMenuItemDTO> getVendors() {
          return vendorRepository.findAll().stream()
                  .map(vendorMapper::mapToMenuItemDTO)
                  .collect(Collectors.toList());
     }

     public Page<TemplateDTO> retrieveAllTemplates(Long vendorId, int pageNo, int pageSize) {
          PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
          Page<Template> page = templateRepository.findByVendor_Id(vendorId, pageRequest);
          return page.map(templateMapper::mapToTemplateDTO);
     }

     public List<ProductLadderItem> getTopSellingProductsForVendor(Long vendorId) {
          List<Object[]> results = vendorRepository.TOP_SELLERS_FOR_VENDOR(vendorId);
          List<ProductLadderItem> products = new ArrayList<>();

          for (Object[] result : results) {
               Long id = (Long) result[0];
               BigDecimal totalSales = (BigDecimal) result[1];
               Product p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
               ProductLadderItem item = new ProductLadderItem();
               modelMapper.map(p, item);
               item.setProfitAmount(p.getProfit() * totalSales.floatValue());
               item.setStocksSold(totalSales.longValue());
               products.add(item);
          }
          return products;
     }
}
