package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.dto.commons.ProductLadderItem;
import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import lombok.Data;

import java.util.List;

@Data
public class VendorDetails {
     private VendorDTO vendorDTO;
     private SalesAnalysis salesAnalysis;
     private List<ProductLadderItem> productLadder;
}
