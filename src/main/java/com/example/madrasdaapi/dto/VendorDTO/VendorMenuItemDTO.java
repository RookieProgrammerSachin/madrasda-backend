package com.example.madrasdaapi.dto.VendorDTO;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Vendor} entity
 */
@Data
public class VendorMenuItemDTO implements Serializable {
     private Long id;
     private String name;
     private String imgUrl;
}