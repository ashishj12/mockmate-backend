package com.mockmate.mockmate_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.CoverLetter;
import com.mockmate.mockmate_backend.entity.User;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Long> {

	Optional<CoverLetter> findByIdAndUser(Long id, User user);
}