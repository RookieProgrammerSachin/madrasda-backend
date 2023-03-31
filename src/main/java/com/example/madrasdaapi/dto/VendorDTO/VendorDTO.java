package com.example.madrasdaapi.dto.VendorDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class VendorDTO implements Serializable {
     private Long id;
     private String name;
     private String imgUrl;
}
