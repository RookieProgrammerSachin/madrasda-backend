package com.example.madrasdaapi.models;

import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link Mockup} entity
 */
@Getter
@Setter
public class MockupDTO implements Serializable {
     private Long id;
     private String imgPath;
     private String name;
     private String color;
     private String size;
     private Integer quantity;
     private String model;
     private String type;
     private String category;
     private VendorDTO vendor;
}