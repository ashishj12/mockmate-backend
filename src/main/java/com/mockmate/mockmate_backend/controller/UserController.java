package com.mockmate.mockmate_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockmate.mockmate_backend.dto.request.UpdateUserProfileRequest;
import com.mockmate.mockmate_backend.dto.response.ApiResponse;
import com.mockmate.mockmate_backend.dto.response.UserProfileResponse;
import com.mockmate.mockmate_backend.service.UserService;
import com.mockmate.mockmate_backend.util.ResponseBuilder;
import com.mockmate.mockmate_backend.util.SecurityUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/profile")
	public ResponseEntity<ApiResponse<UserProfileResponse>> getProfile() {

		String clerkUserId = SecurityUtils.getCurrentClerkUserId();

		UserProfileResponse response = userService.getProfile(clerkUserId);

		return ResponseEntity.ok(ResponseBuilder.success("Profile fetched successfully", response));
	}

	@PutMapping("/profile")
	public ResponseEntity<ApiResponse<UserProfileResponse>> updateProfile(
			@Valid @RequestBody UpdateUserProfileRequest request) {

		String clerkUserId = SecurityUtils.getCurrentClerkUserId();

		UserProfileResponse response = userService.updateProfile(clerkUserId, request);

		return ResponseEntity.ok(ResponseBuilder.success("Profile updated successfully", response));
	}
}