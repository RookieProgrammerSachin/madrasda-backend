package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}