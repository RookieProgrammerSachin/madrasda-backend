package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.AuthDTO.JwtDTO;
import com.example.madrasdaapi.dto.AuthDTO.LoginDTO;
import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.services.commons.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication Resource Controller")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService authService;
    @PostMapping("/register")
    public ResponseEntity<JwtDTO> register(@RequestBody RegisterDTO request) throws Exception {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> authenticate(@RequestBody LoginDTO request) throws Exception {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
