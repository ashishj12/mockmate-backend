package com.mockmate.mockmate_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.IndustryInsight;
import com.mockmate.mockmate_backend.entity.User;

public interface IndustryInsightRepository extends JpaRepository<IndustryInsight, Long> {

	Optional<IndustryInsight> findByUser(User user);
}