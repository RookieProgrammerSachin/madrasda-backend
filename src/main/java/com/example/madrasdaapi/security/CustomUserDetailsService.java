package com.example.madrasdaapi.security;

import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
     private final UserRepository repository;
     private final UserRepository userRepository;


     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
          return config.getAuthenticationManager();
     }

     @Bean
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }

     @Override
     public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
          User user = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found with phone or email: " + emailOrPhone));
         Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(user.getRole()));

         return new org.springframework.security.core.userdetails.User(
                 emailOrPhone,
                 (user.getPassword() != null)? user.getPassword() : "",
                 authorities
         );

     }
}
