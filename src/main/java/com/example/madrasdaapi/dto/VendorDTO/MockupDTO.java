package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.Size;
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
     private List<Color> colors;
     private List<Size> sizes;
     private String additionalInformation;
}