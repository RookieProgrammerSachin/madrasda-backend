package com.example.madrasdaapi.dto.VendorDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class TemplateDTO implements Serializable {
     private Long id;
     private VendorDTO vendor;
     private DesignDTO frontDesign;
     private DesignDTO backDesign;
     private MockupDTO mockup;

}
