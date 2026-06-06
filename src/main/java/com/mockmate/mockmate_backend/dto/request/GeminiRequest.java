package com.mockmate.mockmate_backend.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeminiRequest {

	private String prompt;
}