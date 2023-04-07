package com.example.madrasdaapi.dto.VendorDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class VendorDTO implements Serializable {
     private Long id;
     private String name;
     private String email;
     private String imgUrl;
     private String phone;
     private String companyName;
     private String companyUrl;

}
