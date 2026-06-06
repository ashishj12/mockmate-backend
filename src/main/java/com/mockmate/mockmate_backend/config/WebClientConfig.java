package com.mockmate.mockmate_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Value("${gemini.base-url}")
	private String baseUrl;

	@Bean
	public WebClient geminiWebClient() {

		return WebClient.builder().baseUrl(baseUrl).build();
	}
}