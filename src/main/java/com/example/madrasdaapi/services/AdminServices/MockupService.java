package com.example.madrasdaapi.services.AdminServices;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupImageDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupSkuDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.mappers.MockupMapper;
import com.example.madrasdaapi.models.*;
import com.example.madrasdaapi.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service

@RequiredArgsConstructor
public class MockupService {
    private final MockupMapper mockupMapper;
    private final MockupRepository mockupRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ProductSKUMappingRepository productSKUMappingRepository;
    private final MockupImageRepository mockupImageRepository;
    private final ModelMapper mapper;


    public MockupDTO addMockup(@RequestBody MockupDTO mockupDTO) {
        List<MockupSkuDTO> skuDTOS = new ArrayList<>();
        HashMap<Long, Color> colors = new HashMap<>();
        for (MockupSkuDTO skuMapping : mockupDTO.getSkuMapping()) {
            Color color = colorRepository.findById(skuMapping.getColor().getId()).orElseThrow();
            colors.put(color.getId(), color);
            Size size = sizeRepository.findById(skuMapping.getSize().getId()).orElseThrow();
            skuMapping.setColor(color);
            skuMapping.setSize(size);
            skuDTOS.add(skuMapping);
        }
        mockupDTO.setSkuMapping(skuDTOS);
        Mockup detachedMockup = mockupMapper.mapToEntity(mockupDTO);
        List<MockupImage> imageList = new ArrayList<>();
        for (MockupImageDTO imageDTO : mockupDTO.getImages()) {
            MockupImage image = new MockupImage();
            image.setImage(imageDTO.getImage());
            image.setColor(colorRepository.findById(imageDTO.getColorId()).get());
            image.setMockup(detachedMockup);
            imageList.add(image);
        }
        detachedMockup.setImages(imageList);
        detachedMockup.setDisabled(false);
        return mockupMapper.mapToDTO(mockupRepository.save(detachedMockup));
    }
    public MockupDTO updateMockup(Long id, MockupDTO newMockupDetails){
        Mockup oldMockupDetails = mockupRepository.findById(id).orElseThrow();
        if(newMockupDetails.getBasePrice() != null){
            oldMockupDetails.setBasePrice(newMockupDetails.getBasePrice());
        }
        if(newMockupDetails.getName() != null){
            oldMockupDetails.setName(newMockupDetails.getName());
        }
        if(newMockupDetails.getProductType() != null){
            oldMockupDetails.setProductType(newMockupDetails.getProductType());
        }
        if(newMockupDetails.getCategory() != null){
            oldMockupDetails.setCategory(newMockupDetails.getCategory());
        }
        if(newMockupDetails.getAdditionalInformation() != null){
            oldMockupDetails.setAdditionalInformation(newMockupDetails.getAdditionalInformation());
        }
        if(newMockupDetails.getHeight() != null){
            oldMockupDetails.setHeight(newMockupDetails.getHeight());
        }
        if(newMockupDetails.getBreadth() != null) {
            oldMockupDetails.setBreadth(newMockupDetails.getBreadth());
        }
        if(newMockupDetails.getWeight() != null){
            oldMockupDetails.setWeight(newMockupDetails.getWeight());
        }
        if(newMockupDetails.getTax() != null){
            oldMockupDetails.setTax(newMockupDetails.getTax());
        }
        if(newMockupDetails.getHsn() != null){
            oldMockupDetails.setHsn(newMockupDetails.getHsn());
        }
        if(newMockupDetails.getBasePrice() != null){
            oldMockupDetails.setBasePrice(newMockupDetails.getBasePrice());
        }
        return mockupMapper.mapToDTO(mockupRepository.save(oldMockupDetails));
    }

     /*public MockupDTO updateMockup(MockupDTO mockupDTO) {
          Mockup detachedMockup = mockupRepository.findById(mockupDTO.getId()).get();
          mapper.getConfiguration().setSkipNullEnabled(true);
          mapper.map(mockupDTO, detachedMockup);
          if(mockupDTO.getColors() != null) detachedMockup.setColors(mockupDTO.getColors());
          if(mockupDTO.getSizes() != null) detachedMockup.setSizes(mockupDTO.getSizes());
          return mockupMapper.mapToDTO(mockupRepository.saveOrUpdate(detachedMockup));
     }*/

    public void deleteMockup(Long id) {
        Mockup detachedMockup = mockupRepository.findById(id).orElseThrow();
        for (ProductSKUMapping sku : detachedMockup.getSkuMapping()) {
            productSKUMappingRepository.deleteById(sku.getId());
        }
        mockupRepository.deleteById(id);
    }

    public MockupDTO getMockupById(Long id) {
        return mockupMapper.mapToDTO(mockupRepository.findById(id).get());
    }

    public Page<MockupDTO> getAllMockups(int pageNo, int pageSize) {
        return mockupRepository.findAllByDisabled(false, PageRequest.of(pageNo, pageSize)).map(mockupMapper::mapToDTO);
    }

    public void disableMockup(Long id) {
        int n = mockupRepository.updateDisabledById(id);
        if (n != 1) throw new APIException("Mockup not found", HttpStatus.NOT_FOUND);

    }

    public MockupDTO updateMockup(@RequestBody MockupDTO mockupDTO) {
        // Retrieve the existing mockup from the database.
         Mockup detachedMockup = mockupMapper.mapToEntity(mockupDTO);
         // Remove all existing images associated with the mockup.
        // Add the new images to the mockup.
         mockupImageRepository.deleteByMockup_Id(mockupDTO.getId());
        List<MockupImage> imageList = new ArrayList<>();
        for (MockupImageDTO imageDTO : mockupDTO.getImages()) {
            MockupImage image = new MockupImage();
            image.setImage(imageDTO.getImage());
            image.setColor(colorRepository.findById(imageDTO.getColorId()).get());
            image.setMockup(detachedMockup);
            imageList.add(image);
        }
         detachedMockup.setImages(imageList);
        // Save the modified mockup to the database.
        Mockup updatedMockup = mockupRepository.save(detachedMockup);
        return mockupMapper.mapToDTO(updatedMockup);
    }
    @Transactional
    public int toggleColorSku(Long colorId, Long mockupId) {
        return productSKUMappingRepository.updateStatusByColor(colorId, mockupId);
    }
}

