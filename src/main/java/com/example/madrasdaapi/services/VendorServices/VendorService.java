package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.VendorDTO.TemplateDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDetails;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.dto.commons.ProductLadderItem;
import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import com.example.madrasdaapi.mappers.TemplateMapper;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.Template;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.TemplateRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import com.example.madrasdaapi.utils.ProcedureCaller;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorService {
     private final VendorMapper vendorMapper;
     private final TemplateMapper templateMapper;
     private final ProcedureCaller caller;
     private final ModelMapper modelMapper;
     private final VendorRepository vendorRepository;
     private final TemplateRepository templateRepository;
     private final ProductRepository productRepository;
     private final UserRepository userRepository;

     @Transactional
     public VendorDetails getVendorDetails(User vendor) {
          VendorDetails vendorDetails = new VendorDetails();
          Vendor v = vendorRepository.findById(vendor.getId()).orElseThrow();
          VendorDTO vendorDTO = vendorMapper.mapToDTO(v);
          vendorDTO.setPhone(vendor.getPhone());
          vendorDetails.setVendor(vendorDTO);
          if(caller.getMonthlySalesByVendorId(vendor.getId()) != null){
               SalesAnalysis salesAnalysis = vendorRepository.getSalesAnalysisByVendorId(vendor.getId());
               salesAnalysis.setMonthlySales(caller.getMonthlySalesByVendorId(vendor.getId()));
               vendorDetails.setSalesAnalysis(salesAnalysis);
               Long profit = vendorDetails.getSalesAnalysis().getTotalProfit();
               List<ProductLadderItem> pLadder = caller.getTopSellingProductsForVendor(vendor.getId(), 10);
               for(ProductLadderItem p : pLadder){
                    p.setReturnsContribution(Math.round((p.getProfitAmount().floatValue()/profit) * 100.0 * 100.0)/100.0);
               }
               vendorDetails.setProductLadder(pLadder);
               salesAnalysis.setProductsSoldToday(caller.getProductsSoldToday(vendor.getId()));
          }
          vendorDetails.setPayoutRequested(v.getPayoutRequested());
          vendorDetails.setPayoutAmount(v.getOutstandingProfit());
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

     public void updateVendorImage(String imgUrl){
          Vendor vendor = vendorRepository.findByUser_Email(AuthContext.getCurrentUser());
          vendor.setProfilePic(imgUrl);
          vendorRepository.save(vendor);
     }


}
