package com.mockmate.mockmate_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockmate.mockmate_backend.dto.request.GenerateInterviewRequest;
import com.mockmate.mockmate_backend.dto.request.ImprovementTipRequest;
import com.mockmate.mockmate_backend.dto.request.SubmitAssessmentRequest;
import com.mockmate.mockmate_backend.dto.response.ApiResponse;
import com.mockmate.mockmate_backend.dto.response.AssessmentResponse;
import com.mockmate.mockmate_backend.dto.response.ImprovementTipResponse;
import com.mockmate.mockmate_backend.dto.response.InterviewResponse;
import com.mockmate.mockmate_backend.service.InterviewService;
import com.mockmate.mockmate_backend.util.ResponseBuilder;
import com.mockmate.mockmate_backend.util.SecurityUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/interviews")
@RequiredArgsConstructor
public class InterviewController {

	private final InterviewService interviewService;

	@PostMapping("/generate")
	public ResponseEntity<ApiResponse<InterviewResponse>> generate(
			@Valid @RequestBody GenerateInterviewRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.success("Interview questions generated successfully",
						interviewService.generateQuestions(SecurityUtils.getCurrentClerkUserId(), request)));
	}

	@PostMapping("/submit")
	public ResponseEntity<ApiResponse<AssessmentResponse>> submit(@Valid @RequestBody SubmitAssessmentRequest request) {

		return ResponseEntity.ok(ResponseBuilder.success("Assessment submitted successfully",
				interviewService.submitAssessment(SecurityUtils.getCurrentClerkUserId(), request)));
	}

	@GetMapping("/history")
	public ResponseEntity<ApiResponse<List<AssessmentResponse>>> history() {

		return ResponseEntity.ok(ResponseBuilder.success("Assessment history fetched successfully",
				interviewService.getHistory(SecurityUtils.getCurrentClerkUserId())));
	}

	@PostMapping("/improvement-tip")
	public ResponseEntity<ApiResponse<ImprovementTipResponse>> tip(@Valid @RequestBody ImprovementTipRequest request) {

		return ResponseEntity.ok(ResponseBuilder.success("Improvement tips generated successfully",
				interviewService.generateImprovementTip(SecurityUtils.getCurrentClerkUserId(), request)));
	}
}