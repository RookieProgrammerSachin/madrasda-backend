package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.dto.VendorDTO.VendorMenuItemDTO;
import com.example.madrasdaapi.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Product} entity
 */
@Getter
@Setter
public class NewProductDTO implements Serializable {
    private Long id;
    private String name;
    private String audience;
    private String description;
    private Float basePrice;
    private Float shipping;
    private Float discount;
    private Float total;
    private Float profit;
    private Float tax;
    private Boolean publishStatus;
    private VendorMenuItemDTO vendor;
    private List<ProductImageDTO> productImages;
    private String frontDesignUrl;
    private String frontDesignPlacement;
    private String backDesignUrl;
    private String backDesignPlacement;
    private Long mockupId;
    private List<Long> colors;

}