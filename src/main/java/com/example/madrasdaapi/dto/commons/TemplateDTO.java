package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.dto.VendorDTO.DesignDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Template} entity
 */
@Getter
@Setter
public class TemplateDTO implements Serializable {
     private Long id;
     private MockupDTO mockup;
     private VendorDTO vendor;
     @NotNull
     private DesignDTO frontDesign;
     @NotNull
     private DesignDTO backDesign;
}