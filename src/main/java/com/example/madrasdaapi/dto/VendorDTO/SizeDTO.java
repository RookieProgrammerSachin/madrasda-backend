package com.example.madrasdaapi.dto.VendorDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Size} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO implements Serializable {
    private Long id;
    private String size;
}