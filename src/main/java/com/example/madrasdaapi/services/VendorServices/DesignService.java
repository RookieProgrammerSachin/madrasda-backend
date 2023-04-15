package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.DesignDTO;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.mappers.DesignMapper;
import com.example.madrasdaapi.models.Design;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.DesignRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DesignService {
    private final DesignRepository designRepository;
    private final DesignMapper designMapper;
    private final ModelMapper mapper;
    private final VendorRepository vendorRepository;

    public DesignDTO getDesignById(Long id) {
        Long vendor = vendorRepository
                .findIdByUser_Email(SecurityContextHolder.getDeferredContext()
                        .get()
                        .getAuthentication()
                        .getName());
        return designMapper.mapToDTO(designRepository.findByIdAndVendor_Id(id, vendor)
                .orElseThrow(() -> new ResourceNotFoundException("Design", "id", id.toString())));
    }


    public DesignDTO saveOrUpdate(DesignDTO designDTO) {
        Design detachedDesign = designMapper.mapToEntity(designDTO);
        Vendor vendor = vendorRepository
                .findByUser_Email(SecurityContextHolder.getDeferredContext()
                .get()
                .getAuthentication()
                .getName());
          detachedDesign.setVendor(vendor);
        Design savedDesign = designRepository.save(detachedDesign);
        return designMapper.mapToDTO(savedDesign);
    }
    @Transactional
    public void deleteById(Long designId) {
        Long vendor = vendorRepository
                .findIdByUser_Email(SecurityContextHolder.getDeferredContext()
                        .get()
                        .getAuthentication()
                        .getName());
        designRepository.deleteByIdAndVendor_Id(designId, vendor);
    }

    public List<DesignDTO> getAllDesignsByVendor(String email) {
        return vendorRepository.findByUser_Email(email)
                .getDesigns()
                .stream()
                .map(designMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
