package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.ClientDTO.CustomerDto;
import com.example.madrasdaapi.dto.commons.UserDTO;
import com.example.madrasdaapi.models.Customer;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.repositories.CustomerRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public CustomerDto mapToDTO(Customer customer) {
        return mapper.map(customer, CustomerDto.class);
    }
    public User mapToEntity(UserDTO customerDTO) {
        return userRepository.findById(customerDTO.getId()).get();
    }
    public Customer mapToEntity(CustomerDto customerDTO) {
        return mapper.map(customerDTO, Customer.class);
    }
}
