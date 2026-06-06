package com.mockmate.mockmate_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.CoverLetter;
import com.mockmate.mockmate_backend.entity.User;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Long> {

	List<CoverLetter> findByUserOrderByCreatedAtDesc(User user);
}