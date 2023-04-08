package com.example.madrasdaapi.models;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
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