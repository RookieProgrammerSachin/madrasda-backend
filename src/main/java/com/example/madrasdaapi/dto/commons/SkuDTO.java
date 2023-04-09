package com.example.madrasdaapi.dto.commons;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.ProductSKUMapping} entity
 */
@Getter
@Setter
public class SkuDTO implements Serializable {
    private Long id;
    private String sku;
}