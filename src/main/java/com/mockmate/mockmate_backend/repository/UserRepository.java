package com.mockmate.mockmate_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockmate.mockmate_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByClerkUserId(String clerkUserId);

	Optional<User> findByEmail(String email);

	boolean existsByClerkUserId(String clerkUserId);
}