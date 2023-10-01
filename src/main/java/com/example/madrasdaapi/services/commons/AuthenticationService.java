package com.example.madrasdaapi.services.commons;


import com.example.madrasdaapi.dto.AuthDTO.JwtDTO;
import com.example.madrasdaapi.dto.AuthDTO.LoginDTO;
import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.models.enums.Role;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    //    private final Environment env;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    @Value("${twilio.account.sid}")
    private String accSid;
    @Value("${twilio.auth.token}")
    private String twilioAuthToken;
    @Value("${twilio.service.sid}")
    private String serviceSid;
    private final VendorRepository vendorRepository;

    public JwtDTO authenticateAdmin(LoginDTO loginDTO) throws Exception {
        User admin = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", loginDTO.getEmail()));
        if (!admin.getRole().equals("ROLE_ADMIN")) throw new APIException("Bad credentials", HttpStatus.UNAUTHORIZED);
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                (loginDTO.getPassword())));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtService.generateToken(auth);
        return new JwtDTO(jwtToken);
    }

    public JwtDTO authenticateVendor(LoginDTO loginDTO) throws Exception {
        Vendor vendorAccount = vendorRepository.getVendorByUser_Email(loginDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", "email", loginDTO.getEmail()));
        User vendor = vendorAccount.getUser();
        if (!vendor.getRole().equals("ROLE_VENDOR")) throw new BadCredentialsException("Invalid Email or Password");
        if(!vendorAccount.getStatus()) throw new APIException("Account Disabled", HttpStatus.CONFLICT);
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                (loginDTO.getPassword())));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtService.generateToken(auth);
        return new JwtDTO(jwtToken);
    }
    public JwtDTO authenticateCustomer(LoginDTO loginDTO) throws Exception {
        User user = userRepository.findByEmailOrPhone(loginDTO.getEmail(), loginDTO.getEmail()).orElseThrow(
                () -> new APIException("Customer not found", HttpStatus.BAD_REQUEST)
        );
        if(!user.getRole().equals("ROLE_CUSTOMER"))
            throw new BadCredentialsException("Invalid Email or Password");
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                (loginDTO.getPassword())));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtService.generateToken(auth);
        return new JwtDTO(jwtToken);
    }
    public JwtDTO registerCustomer(RegisterDTO registerDTO) throws Exception {
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setRole("ROLE_CUSTOMER");
        user = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
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
        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(
                            serviceSid)
                    .setTo("+91" + phone)
                    .setCode(otp)
                    .create();

            for (int i = 0; i < 8; i++) {
                if (verificationCheck.getStatus().equals("approved")) break;
                Thread.sleep(700);
            }
            if (!verificationCheck.getStatus().equals("approved"))
                throw new APIException("Invalid OTP", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new APIException("Invalid OTP", HttpStatus.BAD_REQUEST); //OTP invalid
        }
        Optional<User> user = userRepository.findByEmailOrPhone(phone, phone);
        if (user.isPresent()) {
            jwtToken = jwtService.generateToken(user.get());
        } else {
            User newUser = User.builder()
                    .phone(phone).build();
            newUser.setRole(Role.ROLE_CUSTOMER.name());
            userRepository.save(newUser);
            jwtToken = jwtService.generateToken(newUser);
        }
        return JwtDTO.builder()
                .token(jwtToken)
                .build();
    }
}
