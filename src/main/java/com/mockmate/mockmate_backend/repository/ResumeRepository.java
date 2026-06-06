package com.mockmate.mockmate_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.Resume;
import com.mockmate.mockmate_backend.entity.User;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

	Optional<Resume> findByUser(User user);

	boolean existsByUser(User user);
}
