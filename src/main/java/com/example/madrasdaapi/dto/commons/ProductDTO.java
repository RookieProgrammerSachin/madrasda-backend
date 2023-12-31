package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
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
    private boolean adminBan;
    private String description;
    private Long vendorId;
    private String vendorImg;
    private List<ColorDTO> colors;
    private Integer quantity;
    private String category;
    private String mockupDescription;
    private MockupDTO productMockup;
    private String frontDesignUrl;
    private String frontDesignPlacement;
    private String backDesignUrl;
}