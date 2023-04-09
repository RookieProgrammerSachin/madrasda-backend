package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
