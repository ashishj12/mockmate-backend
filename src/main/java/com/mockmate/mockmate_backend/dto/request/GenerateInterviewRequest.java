package com.mockmate.mockmate_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateInterviewRequest {

	@NotBlank(message = "Role is required")
	private String role;

	@NotBlank(message = "Level is required")
	private String level;

	@NotBlank(message = "Technology is required")
	private String technology;
}