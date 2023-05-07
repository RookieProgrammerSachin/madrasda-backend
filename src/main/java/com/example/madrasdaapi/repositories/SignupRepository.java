package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.SignupRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignupRepository extends JpaRepository<SignupRequests, Long> {

}
