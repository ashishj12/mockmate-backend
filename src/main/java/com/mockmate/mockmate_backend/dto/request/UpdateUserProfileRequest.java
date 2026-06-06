package com.mockmate.mockmate_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileRequest {

	@NotBlank(message = "Industry is required")
	private String industry;

	@Size(max = 1000, message = "Bio cannot exceed 1000 characters")
	private String bio;

	@NotNull(message = "Experience is required")
	private Integer experience;

	@NotBlank(message = "Skills are required")
	private String skills;
}