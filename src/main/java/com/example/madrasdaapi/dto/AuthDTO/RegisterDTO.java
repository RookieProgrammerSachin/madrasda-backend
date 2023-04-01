package com.example.madrasdaapi.dto.AuthDTO;


import com.example.madrasdaapi.models.Role;
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
    private String password;
    private Role role;
}
