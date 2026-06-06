package com.mockmate.mockmate_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeminiPromptResponse {

	private String response;
}