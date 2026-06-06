package com.mockmate.mockmate_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockmate.mockmate_backend.dto.request.GenerateIndustryInsightRequest;
import com.mockmate.mockmate_backend.dto.response.ApiResponse;
import com.mockmate.mockmate_backend.dto.response.IndustryInsightResponse;
import com.mockmate.mockmate_backend.service.IndustryInsightService;
import com.mockmate.mockmate_backend.util.ResponseBuilder;
import com.mockmate.mockmate_backend.util.SecurityUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/industry-insights")
@RequiredArgsConstructor
public class IndustryInsightController {

	private final IndustryInsightService industryInsightService;

	@PostMapping("/generate")
	public ResponseEntity<ApiResponse<IndustryInsightResponse>> generateInsight(
			@Valid @RequestBody GenerateIndustryInsightRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success("Industry insights generated successfully",
						industryInsightService.generateInsight(SecurityUtils.getCurrentClerkUserId(), request)));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<IndustryInsightResponse>> getInsight() {

		return ResponseEntity.ok(ResponseBuilder.success("Industry insights fetched successfully",
				industryInsightService.getInsight(SecurityUtils.getCurrentClerkUserId())));
	}
}