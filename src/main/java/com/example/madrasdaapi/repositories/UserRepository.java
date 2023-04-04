package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);
     Optional<User> findByPhone(String phone);

     Optional<User> findByEmailOrPhone(String email, String phone);

     Boolean existsByEmail(String email);
}