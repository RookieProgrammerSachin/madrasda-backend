package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.ProductSKUMapping;
import com.example.madrasdaapi.models.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link ProductSKUMapping} entity
 */
@Getter
@Setter
public class ProductSKUMappingDTO implements Serializable {
    private Long id;
    private String sku;
    private MockupDTO mockup;
    private Size size;
    private Color color;
}