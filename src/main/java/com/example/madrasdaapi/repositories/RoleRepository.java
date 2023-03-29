package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}