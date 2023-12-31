package com.example.madrasdaapi.services.AdminServices;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.mappers.MockupMapper;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.PayoutRecord;
import com.example.madrasdaapi.models.SignupRequests;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    private final SignupRepository signupRepository;
    private final PasswordEncoder encoder;
    private final VendorMapper vendorMapper;
    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final MockupRepository mockupRepository;
    private final MockupMapper mockupMapper;

    @Transactional
    public VendorDTO saveOrUpdateVendor(RegisterDTO registerDTO) {
        if(userRepository.findByEmailOrPhone(registerDTO.getEmail(), registerDTO.getPhone()).isPresent()){
            throw new APIException("User Already Exists", HttpStatus.BAD_REQUEST);
        }
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

    @Transactional
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
        if (vendorDTO.getCategory() != null) {
            vendor.setCategory(vendorDTO.getCategory());
        }
        return vendorMapper.mapToDTO(vendorRepository.save(vendor));
    }

    public List<VendorMenuItemDTO> getPayoutRequests() {
        List<Vendor> vendorsList = vendorRepository.findAllByPayoutRequested(true);
        List<VendorMenuItemDTO> vendorMenuItemDTOS = new ArrayList<>();
        for (Vendor v : vendorsList) {
            VendorMenuItemDTO vendorMenuItemDTO = vendorMapper.mapToMenuItemDTOWithPayout(v);
            PayoutRecord payoutRecord = payoutRepository.findByVendor_IdAndPaid(v.getId(), false).orElseThrow();
            vendorMenuItemDTO.setPayoutId(payoutRecord.getId());
            vendorMenuItemDTOS.add(vendorMenuItemDTO);
        }
        return vendorMenuItemDTOS;
    }

    public void updatePassword(String newPassword) {
        User user = userRepository.findByEmailOrPhone(AuthContext.getCurrentUser(), AuthContext.getCurrentUser()).orElseThrow();
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }

    public void saveSignUpRequest(RegisterDTO newVendor) {
        SignupRequests newRequest = new SignupRequests();
        mapper.map(newVendor, newRequest);
        signupRepository.save(newRequest);
    }

    public Page<SignupRequests> getAllSignupRequests(int pageNo, int pageSize) {
        return signupRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    public void removeSignUpRequest(Long id) {
        signupRepository.deleteById(id);
    }

    public VendorDTO appproveVendorSignup(Long id, String password) {
        RegisterDTO newVendor = new RegisterDTO();
        mapper.map(signupRepository.findById(id).get(), newVendor);
        newVendor.setPassword(password);
        VendorDTO vendor = saveOrUpdateVendor(newVendor);
        signupRepository.deleteById(id);
        return vendor;
    }

    @Transactional
    public void toggleVendorAccount(Long vendorId) {
        vendorRepository.toggleVendorAccount(vendorId);
    }

    @Transactional
    public void toggleProduct(Long productId) {
        productRepository.toggleProductStatus(productId);
    }

    @Transactional
    public void deleteVendorProfilePicture(Long vendorId) {
        vendorRepository.deleteVendorProfilePicture(vendorId);
    }

    public Page<MockupDTO> getAllMockups(int pageNo, int pageSize) {
        return mockupRepository.findAll(PageRequest.of(pageNo, pageSize)).map(mockupMapper::mapToDTO);
    }
}
