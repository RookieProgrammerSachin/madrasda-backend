package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendor_signup_requests", schema = "spring-madrasda")
public class SignupRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String influencerCategory;
    private String password;
    private String phone;
    private String imgUrl;
    private String companyName;
    private String companyUrl;
    private String GSTIN;
}
