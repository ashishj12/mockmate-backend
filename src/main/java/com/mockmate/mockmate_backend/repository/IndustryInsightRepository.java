package com.mockmate.mockmate_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.IndustryInsight;

public interface IndustryInsightRepository extends JpaRepository<IndustryInsight, Long> {

	Optional<IndustryInsight> findByUserId(Long userId);
}