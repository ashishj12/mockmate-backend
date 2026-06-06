package com.mockmate.mockmate_backend.ai.prompts;

public final class InterviewPromptBuilder {

	private InterviewPromptBuilder() {
	}

	public static String buildQuestions(String role, String level, String technology) {

		return """
				Generate 10 interview questions.

				Role: %s
				Level: %s
				Technology: %s

				Return JSON array.
				""".formatted(role, level, technology);
	}

	public static String buildImprovementTips(String answers) {

		return """
				Review answers and provide improvement tips.

				Answers:
				%s
				""".formatted(answers);
	}
}