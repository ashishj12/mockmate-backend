package com.mockmate.mockmate_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.Assessment;
import com.mockmate.mockmate_backend.entity.User;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

	List<Assessment> findByUserOrderByCreatedAtDesc(User user);
}
