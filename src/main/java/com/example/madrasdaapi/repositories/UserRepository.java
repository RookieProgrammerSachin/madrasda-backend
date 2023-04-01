package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);
     Optional<User> findByEmailOrPhone(String email, String phone);
}