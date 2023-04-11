package com.example.madrasdaapi.dto.VendorDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Color} entity
 */
@Getter
@Setter
public class ColorDTO implements Serializable {
    private Long id;
    private String color;
    private String hexValue;
}