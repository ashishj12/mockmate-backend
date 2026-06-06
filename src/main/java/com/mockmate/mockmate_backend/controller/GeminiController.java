package com.mockmate.mockmate_backend.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockmate.mockmate_backend.dto.request.GeminiPromptRequest;
import com.mockmate.mockmate_backend.dto.response.ApiResponse;
import com.mockmate.mockmate_backend.dto.response.GeminiPromptResponse;
import com.mockmate.mockmate_backend.service.GeminiService;
import com.mockmate.mockmate_backend.util.ResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/gemini")
@RequiredArgsConstructor
@Profile("dev")
public class GeminiController {

	private final GeminiService geminiService;

	@PostMapping("/generate")
	public ResponseEntity<ApiResponse<GeminiPromptResponse>> generateContent(
			@Valid @RequestBody GeminiPromptRequest request) {

		String result = geminiService.generateContent(request.getPrompt());

		GeminiPromptResponse response = GeminiPromptResponse.builder().response(result).build();

		return ResponseEntity.ok(ResponseBuilder.success("Content generated successfully", response));
	}
}