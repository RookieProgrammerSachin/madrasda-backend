package com.example.madrasdaapi.dto.VendorDTO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Vendor} entity
 */
@Data
public class VendorMenuItemDTO implements Serializable {
     private Long id;
     private String name;
     private String imgUrl;
     private Boolean payoutRequested;
     private BigDecimal payoutAmount;
     private Long payoutId;
}