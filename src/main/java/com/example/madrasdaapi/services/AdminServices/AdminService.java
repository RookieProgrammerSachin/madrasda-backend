package com.example.madrasdaapi.services.AdminServices;

import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
     private final VendorRepository vendorRepository;
     private final UserRepository userRepository;
     private final PasswordEncoder encoder;
     private final VendorMapper vendorMapper;
     private final ModelMapper mapper;

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

          VendorDTO vendor = vendorMapper.mapToDTO(vendorRepository.save(detachedVendor));
          return vendor;
//          return vendorMapper.mapToDTO(newUser.getVendor());
     }

     public void deleteVendor(Long id) {
          vendorRepository.deleteById(id);
     }

     public VendorDTO updateVendor(VendorDTO vendorDTO) {
          Vendor vendor = vendorMapper.mapToEntity(vendorDTO);
          return vendorMapper.mapToDTO(vendorRepository.save(vendor));
     }
}
