package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.dto.commons.ProductDTO;
import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import com.example.madrasdaapi.models.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class VendorDTO implements Serializable {
     private Long id;
     private String name;
     private String imgUrl;
}
