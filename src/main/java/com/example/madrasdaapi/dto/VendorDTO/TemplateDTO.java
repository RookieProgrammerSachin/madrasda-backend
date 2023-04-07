package com.example.madrasdaapi.dto.VendorDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 */
@Getter
@Setter
public class TemplateDTO implements Serializable {
     private Long id;
     private Long vendorId;
     private MockupDTO mockup;
     private DesignDTO frontDesign;
     private String frontDesignPlacement;
     private DesignDTO backDesign;
     private String backDesignPlacement;
     private List<String> colorPalette;
     private List<String> sizes;
     private String additionalInstructions;
}