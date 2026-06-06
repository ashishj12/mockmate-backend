package com.mockmate.mockmate_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateIndustryInsightRequest {

	@NotBlank(message = "Industry is required")
	private String industry;
}