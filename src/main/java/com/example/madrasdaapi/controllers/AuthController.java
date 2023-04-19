package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.AuthDTO.JwtDTO;
import com.example.madrasdaapi.dto.AuthDTO.LoginDTO;
import com.example.madrasdaapi.security.JwtService;
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
    private final JwtService jwtService;

    
    @PostMapping("/loginVendor")
    public ResponseEntity<JwtDTO> authenticateVendor(@RequestBody LoginDTO request) throws Exception {
        return ResponseEntity.ok(authService.authenticateVendor(request));
    }
    @PostMapping("/loginAdmin")
    public ResponseEntity<JwtDTO> authenticateAdmin(@RequestBody LoginDTO request) throws Exception {
        return ResponseEntity.ok(authService.authenticateAdmin(request));
    }

    @PostMapping("/loginClient")
    public ResponseEntity<String> loginClient(@RequestParam String phone) throws Exception {
//        return ResponseEntity.ok("Received");
        return ResponseEntity.ok(authService.generateOTP(phone));
    }
    @PostMapping("/verifyOtp")
    public ResponseEntity<JwtDTO> validate(
            @RequestParam("otp") String otp,
            @RequestParam("phone") String phone
    ) throws Exception {
        JwtDTO token = authService.validateOTP(otp, phone);
        return ResponseEntity.ok(token);
    }
}
