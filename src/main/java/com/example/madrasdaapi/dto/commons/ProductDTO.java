package com.example.madrasdaapi.dto.commons;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Product} entity
 */
@Getter
@Setter
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private BigDecimal profit;
    private BigDecimal discount;
    private BigDecimal total;
    private Boolean publishStatus;
    private String description;
    private List<ColorDTO> colors;
}