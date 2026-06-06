package com.mockmate.mockmate_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AtsAnalysisResponse {

	private Long id;

	private Integer score;

	private String keywords;

	private String missingKeywords;

	private String suggestions;

	private String rawResponse;
}