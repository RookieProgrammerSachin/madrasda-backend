package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.AuthDTO.JwtDTO;
import com.example.madrasdaapi.dto.AuthDTO.LoginDTO;
import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.services.commons.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/loginClient")
    public ResponseEntity<String> loginClient(@RequestParam("phone") String phone) throws Exception {
        return ResponseEntity.ok(authService.generateOTP(phone));
    }
    @GetMapping("/validateClient")
    public ResponseEntity<JwtDTO> validate(
            @RequestParam("otp") String otp,
            @RequestParam("phone") String phone
    ) throws Exception {
        JwtDTO token = authService.validateOTP(otp, phone);
        if(token == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        else
            return ResponseEntity.ok(token);
    }
}
