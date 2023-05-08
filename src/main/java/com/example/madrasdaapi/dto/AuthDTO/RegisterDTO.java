package com.example.madrasdaapi.dto.AuthDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String email;
    private String phone;
    private String influencerCategory;
    private String password;
    private String imgUrl;
    private String companyName;
    private String companyUrl;
    private String GSTIN;
}
