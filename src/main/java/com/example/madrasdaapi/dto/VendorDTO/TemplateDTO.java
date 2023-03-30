package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.models.MockupDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateDTO {
     private DesignDTO design;
     private MockupDTO mockup;
}
