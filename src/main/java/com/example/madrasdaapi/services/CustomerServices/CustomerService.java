package com.example.madrasdaapi.services.CustomerServices;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.AuthDTO.RegisterDTO;
import com.example.madrasdaapi.dto.commons.UserDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final UserRepository userRepository;
    public void updateProfile(RegisterDTO request){
        String emailOrPhone = AuthContext.getCurrentUser();
        User user = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone).orElseThrow(
                () -> new APIException("User not found", HttpStatus.BAD_REQUEST)
        );
        if(request.getPhone() != null)
            user.setPhone(request.getPhone());
        if(request.getEmail() != null)
            user.setEmail(request.getEmail());
        if(request.getName() != null)
            user.setName(request.getName());
        userRepository.save(user);
    }
    public UserDTO getUserProfile(){
        String emailOrPhone = AuthContext.getCurrentUser();
        User user = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone).orElseThrow(
                () -> new APIException("User not found", HttpStatus.BAD_REQUEST)
        );
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
