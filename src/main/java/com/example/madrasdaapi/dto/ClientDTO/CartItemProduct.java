package com.example.madrasdaapi.dto.ClientDTO;

import com.example.madrasdaapi.dto.commons.ColorDTO;
import com.example.madrasdaapi.dto.commons.ProductImageDTO;
import com.example.madrasdaapi.dto.commons.SizeDTO;
import lombok.Data;

@Data
public class CartItemProduct {
    private Long id;
    private String name;
    private Float discount;
    private Float total;
    private ColorDTO colorDTO;
    private SizeDTO sizeDTO;
    private ProductImageDTO frontImage;
}
