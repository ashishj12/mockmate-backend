package com.mockmate.mockmate_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockmate.mockmate_backend.dto.request.CreateResumeRequest;
import com.mockmate.mockmate_backend.dto.request.ImproveResumeRequest;
import com.mockmate.mockmate_backend.dto.request.UpdateResumeRequest;
import com.mockmate.mockmate_backend.dto.response.ApiResponse;
import com.mockmate.mockmate_backend.dto.response.ResumeResponse;
import com.mockmate.mockmate_backend.service.ResumeService;
import com.mockmate.mockmate_backend.util.ResponseBuilder;
import com.mockmate.mockmate_backend.util.SecurityUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {

	private final ResumeService resumeService;

	@PostMapping
	public ResponseEntity<ApiResponse<ResumeResponse>> createResume(@Valid @RequestBody CreateResumeRequest request) {

		ResumeResponse response = resumeService.createResume(SecurityUtils.getCurrentClerkUserId(), request);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success("Resume created successfully", response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<ResumeResponse>> getResume() {

		ResumeResponse response = resumeService.getResume(SecurityUtils.getCurrentClerkUserId());

		return ResponseEntity.ok(ResponseBuilder.success("Resume fetched successfully", response));
	}

	@PutMapping
	public ResponseEntity<ApiResponse<ResumeResponse>> updateResume(@Valid @RequestBody UpdateResumeRequest request) {

		ResumeResponse response = resumeService.updateResume(SecurityUtils.getCurrentClerkUserId(), request);

		return ResponseEntity.ok(ResponseBuilder.success("Resume updated successfully", response));
	}

	@PostMapping("/improve")
	public ResponseEntity<ApiResponse<ResumeResponse>> improveResume(@Valid @RequestBody ImproveResumeRequest request) {

		ResumeResponse response = resumeService.improveResume(SecurityUtils.getCurrentClerkUserId(), request);

		return ResponseEntity.ok(ResponseBuilder.success("Resume improved successfully", response));
	}
}