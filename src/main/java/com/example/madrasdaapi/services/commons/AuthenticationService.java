package com.example.madrasdaapi.services.commons;


import com.example.madrasdaapi.dto.AuthDTO.JwtDTO;
import com.example.madrasdaapi.dto.AuthDTO.LoginDTO;
import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.models.Role;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.security.JwtService;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Value("${twilio.account.sid}")
    private String accSid;
    @Value("${twilio.auth.token}")
    private String twilioAuthToken;
    @Value("${twilio.service.sid}")
    private String serviceSid;
//    private final Environment env;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public JwtDTO register(RegisterDTO registerDTO) throws Exception {
        User user = User.builder()
                .phone(registerDTO.getEmail())
                .role("ROLE_VENDOR")
                .build();
        userRepository.save(user);
        return authenticate(new LoginDTO(registerDTO.getEmail(), registerDTO.getPassword(), ""));
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

    public String generateOTP(String phone) throws Exception {
        Twilio.init(accSid, twilioAuthToken);
        Verification verification = Verification.creator(
                serviceSid,
                "+91" + phone,
                "sms"
        ).create();
        return "Your OTP has been sent to your verified phone number";
    }

    public JwtDTO validateOTP(String otp, String phone) throws Exception {
        String jwtToken;
        Twilio.init(accSid, twilioAuthToken);
        try{
            VerificationCheck verificationCheck =  VerificationCheck.creator(
                    serviceSid)
                    .setTo("+91" + phone)
                    .setCode(otp)
                    .create();
            System.out.println(verificationCheck.getStatus());
        }catch (Exception e) {
            return null; //OTP invalid
        }
        Optional<User> user = userRepository.findByPhone(phone);
        if(user.isPresent()) {
            jwtToken = jwtService.generateToken(user.get());
        }else{
            User newUser = User.builder()
                    .phone(phone).build();
            newUser.setRole(Role.ROLE_USER.name());
            userRepository.save(newUser);
            jwtToken = jwtService.generateToken(newUser);
        }
        return JwtDTO.builder()
                .token(jwtToken)
                .build();
    }
}
