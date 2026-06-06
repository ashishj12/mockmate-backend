package com.mockmate.mockmate_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IndustryInsightResponse {

	private Long id;

	private String industry;

	private String salaryRanges;

	private String growthRate;

	private String demandLevel;

	private String topSkills;

	private String marketOutlook;

	private String keyTrends;

	private String recommendedSkills;
}