package com.mockmate.mockmate_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResumeRequest {

	@NotBlank(message = "Resume content is required")
	private String content;
}