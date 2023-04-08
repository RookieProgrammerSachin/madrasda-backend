package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.Mockup;
import com.example.madrasdaapi.models.ProductSKUMapping;
import com.example.madrasdaapi.models.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MockupSkuDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String sku;

    private Size size;

    private Color color;
}
