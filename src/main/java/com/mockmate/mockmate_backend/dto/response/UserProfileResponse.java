package com.mockmate.mockmate_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {

	private Long id;

	private String clerkUserId;

	private String email;

	private String name;

	private String imageUrl;

	private String industry;

	private String bio;

	private Integer experience;

	private String skills;
}