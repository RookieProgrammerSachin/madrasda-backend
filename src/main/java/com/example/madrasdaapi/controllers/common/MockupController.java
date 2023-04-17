package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.mappers.MockupMapper;
import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.repositories.MockupRepository;
import com.example.madrasdaapi.services.AdminServices.MockupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/mockup")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MockupController {
     private final MockupService mockupService;
     private final MockupRepository mockupRepository;
     private final MockupMapper mockupMapper;

     @GetMapping("getMockup/{id}")

     public MockupDTO retrieveMockup(@PathVariable Long id) {

          return mockupService.getMockupById(id);
     }

     @PostMapping("addMockup")
     public MockupDTO saveOrUpdateMockup(@RequestBody MockupDTO mockupDTO) {
          return mockupService.addMockup(mockupDTO);
     }

     @GetMapping("getAllMockups")
     public Page<MockupDTO> getAllMockups(@RequestParam(defaultValue = "0") int pageNo,
                                          @RequestParam(defaultValue = "100") int pageSize) {

          return mockupService.getAllMockups(pageNo, pageSize);
     }

     @DeleteMapping("deleteMockup/{id}")
     public void deleteMockup(@PathVariable Long id) {
          mockupService.deleteMockup(id);
     }


}
