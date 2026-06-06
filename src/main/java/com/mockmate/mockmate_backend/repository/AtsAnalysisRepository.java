package com.mockmate.mockmate_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.AtsAnalysis;
import com.mockmate.mockmate_backend.entity.Resume;

public interface AtsAnalysisRepository extends JpaRepository<AtsAnalysis, Long> {

	Optional<AtsAnalysis> findByResume(Resume resume);
}