package com.example.madrasdaapi.dto.VendorDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
}