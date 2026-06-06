package com.mockmate.mockmate_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.AtsAnalysis;

public interface AtsAnalysisRepository extends JpaRepository<AtsAnalysis, Long> {

	Optional<AtsAnalysis> findByResumeId(Long resumeId);
}