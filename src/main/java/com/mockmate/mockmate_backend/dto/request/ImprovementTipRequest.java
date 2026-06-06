package com.mockmate.mockmate_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImprovementTipRequest {

	@NotBlank(message = "Answers are required")
	private String answers;
}