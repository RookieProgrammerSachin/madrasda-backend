package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
     Page<Feedback> findByResolution(Boolean resolution, Pageable pageable);
}