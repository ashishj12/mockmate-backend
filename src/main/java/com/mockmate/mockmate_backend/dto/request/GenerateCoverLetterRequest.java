package com.mockmate.mockmate_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateCoverLetterRequest {

	@NotBlank(message = "Company name is required")
	private String companyName;

	@NotBlank(message = "Job title is required")
	private String jobTitle;

	@NotBlank(message = "Job description is required")
	private String jobDescription;
}