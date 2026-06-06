package com.mockmate.mockmate_backend.ai.prompts;

public final class ResumePromptBuilder {

	private ResumePromptBuilder() {
	}

	public static String build(String resumeContent, String userPrompt) {

		return """
				Improve the following resume.

				Instructions:
				%s

				Resume:
				%s

				Return only the improved resume.
				""".formatted(userPrompt, resumeContent);
	}
}