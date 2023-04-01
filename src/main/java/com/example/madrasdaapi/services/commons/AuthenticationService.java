package com.example.madrasdaapi.security;


import com.example.madrasdaapi.models.Role;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        var user = User.builder()
                .name(request.getName()!=null ? request.getName() : null)
                .email(request.getEmail()!=null ? request.getEmail() : null)
                .phone(request.getPhone())
                .password(request.getPassword()!=null ? passwordEncoder.encode(request.getPassword()) : null)
                .role(request.getRole())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken((user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        Optional<User> user;
        String jwtToken;
        if(request.getPassword()!=null){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }
        user = repository.findByEmailOrPhone(
                request.getEmail(),
                request.getPhone()
                );
        if(user.isEmpty() && request.getPhone()!=null){
            User userNew = null;
            var newUser = User.builder()
                    .phone(request.getPhone())
                    .role(Role.ROLE_USER)
                    .build();
            repository.save(newUser);
            userNew = newUser;
            jwtToken = jwtService.generateToken(newUser);
        }else{
            jwtToken = jwtService.generateToken(user.get());
        }
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
