package com.mockmate.mockmate_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockmate.mockmate_backend.dto.request.GenerateCoverLetterRequest;
import com.mockmate.mockmate_backend.dto.response.ApiResponse;
import com.mockmate.mockmate_backend.dto.response.CoverLetterResponse;
import com.mockmate.mockmate_backend.service.CoverLetterService;
import com.mockmate.mockmate_backend.util.ResponseBuilder;
import com.mockmate.mockmate_backend.util.SecurityUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cover-letters")
@RequiredArgsConstructor
public class CoverLetterController {

	private final CoverLetterService coverLetterService;

	@PostMapping
	public ResponseEntity<ApiResponse<CoverLetterResponse>> generateCoverLetter(
			@Valid @RequestBody GenerateCoverLetterRequest request) {

		CoverLetterResponse response = coverLetterService.generateCoverLetter(SecurityUtils.getCurrentClerkUserId(),
				request);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success("Cover letter generated successfully", response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<CoverLetterResponse>>> getHistory() {

		return ResponseEntity.ok(ResponseBuilder.success("Cover letters fetched successfully",
				coverLetterService.getHistory(SecurityUtils.getCurrentClerkUserId())));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CoverLetterResponse>> getById(@PathVariable Long id) {

		return ResponseEntity.ok(ResponseBuilder.success("Cover letter fetched successfully",
				coverLetterService.getById(SecurityUtils.getCurrentClerkUserId(), id)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCoverLetter(@PathVariable Long id) {

		coverLetterService.deleteCoverLetter(SecurityUtils.getCurrentClerkUserId(), id);

		return ResponseEntity.noContent().build();
	}
}