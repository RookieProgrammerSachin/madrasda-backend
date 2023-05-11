package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.models.Mockup;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MockupRepository extends JpaRepository<Mockup, Long> {
    @Transactional
    @Modifying
    @Query("update Mockup m set m.disabled = (NOT m.disabled) where m.id = ?1")
    int updateDisabledById(Long id);

    Page<Mockup> findAllByDisabled(boolean isDisabled, Pageable pageable);
}