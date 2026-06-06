package com.mockmate.mockmate_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockmate.mockmate_backend.dto.request.AtsAnalysisRequest;
import com.mockmate.mockmate_backend.dto.response.ApiResponse;
import com.mockmate.mockmate_backend.dto.response.AtsAnalysisResponse;
import com.mockmate.mockmate_backend.service.AtsService;
import com.mockmate.mockmate_backend.util.ResponseBuilder;
import com.mockmate.mockmate_backend.util.SecurityUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ats")
@RequiredArgsConstructor
public class AtsController {

	private final AtsService atsService;

	@PostMapping("/analyze")
	public ResponseEntity<ApiResponse<AtsAnalysisResponse>> analyzeResume(
			@Valid @RequestBody AtsAnalysisRequest request) {

		AtsAnalysisResponse response = atsService.analyzeResume(SecurityUtils.getCurrentClerkUserId(), request);

		return ResponseEntity.ok(ResponseBuilder.success("Resume analyzed successfully", response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<AtsAnalysisResponse>> getAnalysis() {

		AtsAnalysisResponse response = atsService.getAnalysis(SecurityUtils.getCurrentClerkUserId());

		return ResponseEntity.ok(ResponseBuilder.success("ATS analysis fetched successfully", response));
	}
}
