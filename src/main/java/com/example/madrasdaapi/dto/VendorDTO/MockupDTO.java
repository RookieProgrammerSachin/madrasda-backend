package com.example.madrasdaapi.dto.VendorDTO;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Mockup} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MockupDTO implements Serializable {
     private Long id;
     private String frontImage;
     private String backImage;
     private String productType;
     private String category;
     private List<String> color;
     private List<String> size;
     private String additionalInformation;
}