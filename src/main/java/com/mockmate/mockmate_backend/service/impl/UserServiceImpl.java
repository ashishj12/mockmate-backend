package com.mockmate.mockmate_backend.service.impl;

import org.springframework.stereotype.Service;

import com.mockmate.mockmate_backend.dto.request.UpdateUserProfileRequest;
import com.mockmate.mockmate_backend.dto.response.UserProfileResponse;
import com.mockmate.mockmate_backend.entity.User;
import com.mockmate.mockmate_backend.exception.ResourceNotFoundException;
import com.mockmate.mockmate_backend.repository.UserRepository;
import com.mockmate.mockmate_backend.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserProfileResponse getProfile(String clerkUserId) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return mapToResponse(user);
	}

	@Override
	public UserProfileResponse updateProfile(String clerkUserId, UpdateUserProfileRequest request) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setIndustry(request.getIndustry());
		user.setBio(request.getBio());
		user.setExperience(request.getExperience());
		user.setSkills(request.getSkills());

		User savedUser = userRepository.save(user);

		return mapToResponse(savedUser);
	}

	private UserProfileResponse mapToResponse(User user) {

		return UserProfileResponse.builder().id(user.getId()).clerkUserId(user.getClerkUserId()).email(user.getEmail())
				.name(user.getName()).imageUrl(user.getImageUrl()).industry(user.getIndustry()).bio(user.getBio())
				.experience(user.getExperience()).skills(user.getSkills()).build();
	}
}