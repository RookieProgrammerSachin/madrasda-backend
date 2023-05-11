package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.MockupImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockupImageRepository extends JpaRepository<MockupImage, Long> {

    void deleteByMockup_Id(Long id);
}
