package com.mockmate.mockmate_backend.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.mockmate.mockmate_backend.exception.AIServiceException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GeminiClient {

	private final WebClient geminiWebClient;

	@Value("${gemini.api-key}")
	private String apiKey;

	@Value("${gemini.model}")
	private String model;

	public String generate(String prompt) {

		try {

			String endpoint = "/v1beta/models/" + model + ":generateContent?key=" + apiKey;

			String body = """
					{
					  "contents": [
					    {
					      "parts": [
					        {
					          "text": "%s"
					        }
					      ]
					    }
					  ]
					}
					""".formatted(prompt.replace("\"", "\\\""));

			return geminiWebClient.post().uri(endpoint).bodyValue(body).retrieve().bodyToMono(String.class).block();

		} catch (Exception ex) {

			throw new AIServiceException("Gemini API request failed");
		}
	}
}
