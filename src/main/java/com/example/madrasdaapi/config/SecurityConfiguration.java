package com.example.madrasdaapi.config;

import com.example.madrasdaapi.security.JWTAuthenticationEntryPoint;
import com.example.madrasdaapi.security.JwtAuthenticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SecurityConfiguration {
     private final JwtAuthenticationFilter jwtAuthFilter;
     private final JWTAuthenticationEntryPoint authenticationEntryPoint;


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
                  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                  .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
                  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
          ;
          return http.build();
     }

     @Profile("dev")
     @Bean
     public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
          http.authorizeHttpRequests(authorizeRequests ->
                  authorizeRequests
                          .requestMatchers(EndpointRequest.to("info", "health", "refresh")).permitAll()
                          .anyRequest().permitAll()
          )
                  .csrf().disable();
          return http.build();
     }
}
