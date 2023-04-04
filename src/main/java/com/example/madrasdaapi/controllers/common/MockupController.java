package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.services.AdminServices.MockupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/mockup")
@RestController
@RequiredArgsConstructor
public class MockupController {
     private final MockupService mockupService;
     @GetMapping("getMockup/{id}")
     public MockupDTO retrieveMockup(@PathVariable Long id){
          return mockupService.getMockupById(id);
     }

     @PostMapping("addMockup")
     public MockupDTO saveOrUpdateMockup(@RequestBody MockupDTO mockupDTO){
          return mockupService.addMockup(mockupDTO);
     }

     @PutMapping("updateMockup")
     public MockupDTO updateMockup(@RequestBody MockupDTO mockupDTO) {
          return mockupService.updateMockup(mockupDTO);
     }

     @DeleteMapping("deleteMockup/{id}")
     public void deleteMockup(@PathVariable Long id) {
          mockupService.deleteMockup(id);
     }

}
