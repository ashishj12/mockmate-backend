package com.mockmate.mockmate_backend.ai.prompts;

public final class CoverLetterPromptBuilder {

	private CoverLetterPromptBuilder() {
	}

	public static String build(String company, String role, String jobDescription) {

		return """
				Generate a professional cover letter.

				Company:
				%s

				Role:
				%s

				Job Description:
				%s

				Return only the cover letter.
				""".formatted(company, role, jobDescription);
	}
}
