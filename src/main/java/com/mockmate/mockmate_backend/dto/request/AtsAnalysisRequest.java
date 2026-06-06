package com.mockmate.mockmate_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtsAnalysisRequest {

	@NotBlank(message = "Job description is required")
	private String jobDescription;
}