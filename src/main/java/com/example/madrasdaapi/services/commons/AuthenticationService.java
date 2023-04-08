package com.example.madrasdaapi.services.commons;


import com.example.madrasdaapi.dto.AuthDTO.JwtDTO;
import com.example.madrasdaapi.dto.AuthDTO.LoginDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.models.enums.Role;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.security.CustomUserDetailsService;
import com.example.madrasdaapi.security.JwtService;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final CustomUserDetailsService customUserDetailsService;

    public JwtDTO authenticateAdmin(LoginDTO loginDTO) throws Exception {
        User admin = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(
                () -> new APIException("Bad credentials", HttpStatus.UNAUTHORIZED)
        );
        if (!admin.getRole().equals("ROLE_ADMIN")) throw new APIException("Bad credentials", HttpStatus.UNAUTHORIZED);
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        (loginDTO.getPassword())));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtService.generateToken(auth);
        return new JwtDTO(jwtToken);
    }

    public JwtDTO authenticateVendor(LoginDTO loginDTO) throws Exception {
        User vendor = userRepository.findByEmail(loginDTO.getEmail()).get();
        if (!vendor.getRole().equals("ROLE_VENDOR")) throw new BadCredentialsException("Invalid Email or Password");
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                (loginDTO.getPassword())));

        SecurityContextHolder.getContext().setAuthentication(auth);
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
