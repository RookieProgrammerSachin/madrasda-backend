package com.example.madrasdaapi.services.AdminServices;

import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.PayoutRecord;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.PayoutRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final PayoutRepository payoutRepository;
    private final PasswordEncoder encoder;
    private final VendorMapper vendorMapper;
    private final ModelMapper mapper;

    @Transactional
    public VendorDTO saveOrUpdateVendor(RegisterDTO registerDTO) {
        User detachedUser = new User();
        Vendor detachedVendor = new Vendor();
        mapper.map(registerDTO, detachedUser);

        detachedUser.setPassword(encoder.encode(detachedUser.getPassword()));
        detachedVendor.setUser(detachedUser);

        detachedVendor.setProfilePic(registerDTO.getImgUrl());
        detachedVendor.setCompanyName(registerDTO.getCompanyName());
        detachedVendor.setCompanyUrl(registerDTO.getCompanyUrl());
        detachedVendor.setGSTIN(registerDTO.getGSTIN());
        detachedVendor.getUser().setRole("ROLE_VENDOR");
        User user = userRepository.save(detachedUser);
        detachedVendor.setUser(user);

        return vendorMapper.mapToDTO(vendorRepository.save(detachedVendor));
//          return vendorMapper.mapToDTO(newUser.getVendor());
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    public VendorDTO updateVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorRepository.findByUser_Email(SecurityContextHolder.getDeferredContext()
                .get()
                .getAuthentication()
                .getName());
        if (vendorDTO.getName() != null) {
            User user = vendor.getUser();
            user.setName(vendorDTO.getName());
            vendor.setUser(user);
        }
        if (vendorDTO.getEmail() != null) {
            User user = vendor.getUser();
            user.setEmail(vendorDTO.getEmail());
            vendor.setUser(user);
        }
        if (vendorDTO.getImgUrl() != null) {
            vendor.setProfilePic(vendorDTO.getImgUrl());
        }
        if (vendorDTO.getPhone() != null) {
            User user = vendor.getUser();
            user.setPhone(vendorDTO.getPhone());
            vendor.setUser(user);
        }
        if (vendorDTO.getCompanyUrl() != null) {
            vendor.setCompanyUrl(vendorDTO.getCompanyUrl());
        }
        if (vendorDTO.getCompanyName() != null) {
            vendor.setCompanyName(vendorDTO.getCompanyName());
        }
        if (vendorDTO.getGSTIN() != null) {
            vendor.setGSTIN(vendorDTO.getGSTIN());
        }
        if(vendorDTO.getCategory() != null) {
            vendor.setCategory(vendorDTO.getCategory());
        }
        return vendorMapper.mapToDTO(vendorRepository.save(vendor));
    }

    public List<VendorMenuItemDTO> getPayoutRequests() {
        List<Vendor> vendorsList = vendorRepository.findAllByPayoutRequested(true);
        List<VendorMenuItemDTO> vendorMenuItemDTOS = new ArrayList<>();
        for(Vendor v : vendorsList) {
            VendorMenuItemDTO vendorMenuItemDTO = vendorMapper.mapToMenuItemDTOWithPayout(v);
            PayoutRecord payoutRecord = payoutRepository.findByVendor_IdAndPaid(v.getId(), false).orElseThrow();
            vendorMenuItemDTO.setPayoutId(payoutRecord.getId());
            vendorMenuItemDTOS.add(vendorMenuItemDTO);
        }
        return vendorMenuItemDTOS;
    }
}
