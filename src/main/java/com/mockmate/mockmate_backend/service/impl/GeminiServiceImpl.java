package com.mockmate.mockmate_backend.service.impl;

import org.springframework.stereotype.Service;

import com.mockmate.mockmate_backend.ai.GeminiClient;
import com.mockmate.mockmate_backend.service.GeminiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeminiServiceImpl implements GeminiService {

	private final GeminiClient geminiClient;

	@Override
	public String generateContent(String prompt) {

		return geminiClient.generate(prompt);
	}
}