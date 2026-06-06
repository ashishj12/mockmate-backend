package com.mockmate.mockmate_backend.ai.prompts;

public final class IndustryInsightPromptBuilder {

	private IndustryInsightPromptBuilder() {
	}

	public static String build(String industry) {

		return """
				Generate industry insights for:

				%s

				Return JSON:

				{
				  "salaryRanges": {},
				  "growthRate": "",
				  "demandLevel": "",
				  "topSkills": [],
				  "marketOutlook": "",
				  "keyTrends": [],
				  "recommendedSkills": []
				}
				""".formatted(industry);
	}
}
