package com.example.madrasdaapi.config;

import com.example.madrasdaapi.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
     private final JwtAuthenticationFilter jwtAuthFilter;
     private final AuthenticationProvider authenticationProvider;

     @Profile("prod")
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http
                  .csrf()
                  .disable()
                  .authorizeHttpRequests(authorizeRequests ->
                          authorizeRequests
                                  .requestMatchers("/api/**").permitAll()
                                  .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                  .requestMatchers("/api/vendor/**").hasAnyRole("VENDOR", "ADMIN")
                                  .requestMatchers("/api/templates/**").hasAnyRole("VENDOR", "ADMIN")
                                  .requestMatchers("/api/feedback/**").hasAnyRole("VENDOR", "ADMIN")
                                  .anyRequest().authenticated()
                  )
                  .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  .and()
                  .authenticationProvider(authenticationProvider)
                  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
          ;
          return http.build();
     }

     @Profile("dev")
     @Bean
     public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
          http.authorizeHttpRequests(authorizeRequests ->
                  authorizeRequests.anyRequest().permitAll()
          )
                  .csrf().disable();
          return http.build();
     }
}
