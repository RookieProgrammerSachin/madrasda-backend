package com.example.madrasdaapi.services.commons;


import com.example.madrasdaapi.dto.AuthDTO.JwtDTO;
import com.example.madrasdaapi.dto.AuthDTO.LoginDTO;
import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public JwtDTO register(RegisterDTO registerDTO) throws Exception {
        User user = User.builder()
                .phone(registerDTO.getPhoneOrEmail())
                .role("ROLE_USER")
                .build();
        userRepository.save(user);
        return authenticate(new LoginDTO(registerDTO.getPhoneOrEmail(), registerDTO.getPassword(), ""));
    }

    public JwtDTO authenticate(LoginDTO loginDTO) throws Exception {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmailOrPhone(),
                        (loginDTO.getPassword())));

        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userRepository.findByEmailOrPhone(loginDTO.getEmailOrPhone(),loginDTO.getEmailOrPhone()).orElseThrow();
        String jwtToken = jwtService.generateToken(auth);
        return new JwtDTO(jwtToken);
    }
}
