package com.example.madrasdaapi.dto.commons;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Product} entity
 */
@Getter
@Setter
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private Float discount;
    private Float total;
    private List<ColorDTO> colors;
}