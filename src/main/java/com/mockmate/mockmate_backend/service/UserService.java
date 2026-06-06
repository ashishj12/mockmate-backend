package com.mockmate.mockmate_backend.service;

import com.mockmate.mockmate_backend.dto.request.UpdateUserProfileRequest;
import com.mockmate.mockmate_backend.dto.response.UserProfileResponse;

public interface UserService {

	UserProfileResponse getProfile(String clerkUserId);

	UserProfileResponse updateProfile(String clerkUserId, UpdateUserProfileRequest request);
}