package com.mockmate.mockmate_backend.ai.prompts;

public final class AtsPromptBuilder {

	private AtsPromptBuilder() {
	}

	public static String build(String resume, String jobDescription) {

		return """
				Analyze this resume against the job description.

				Resume:
				%s

				Job Description:
				%s

				Return JSON:

				{
				  "score": 0,
				  "keywords": [],
				  "missingKeywords": [],
				  "suggestions": []
				}
				""".formatted(resume, jobDescription);
	}
}