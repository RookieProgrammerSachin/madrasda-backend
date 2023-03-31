package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", schema = "madrasda")
public class User implements UserDetails {
     @Id
     @Column(name = "id", nullable = false)
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "name")
     private String name;

     @Column(name = "email", unique = true)
     private String email;

     @Column(name = "phone",unique = true, length = 11)
     private String phone;

     @Column(name = "password")
     private String password;
     @Enumerated(EnumType.STRING)
     private Role role;
     @OneToOne(mappedBy = "user")
     private Vendor vendor;

     @OneToOne(mappedBy = "user")
     private Customer customer;

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          return List.of(new SimpleGrantedAuthority(role.name()));
     }

     @Override
     public String getUsername() {
          return email;
     }

     @Override
     public boolean isAccountNonExpired() {
          return true;
     }

     @Override
     public boolean isAccountNonLocked() {
          return true;
     }

     @Override
     public boolean isCredentialsNonExpired() {
          return true;
     }

     @Override
     public boolean isEnabled() {
          return true;
     }
}