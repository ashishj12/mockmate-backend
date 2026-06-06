package com.mockmate.mockmate_backend.service;

import java.util.List;

import com.mockmate.mockmate_backend.dto.request.GenerateInterviewRequest;
import com.mockmate.mockmate_backend.dto.request.ImprovementTipRequest;
import com.mockmate.mockmate_backend.dto.request.SubmitAssessmentRequest;
import com.mockmate.mockmate_backend.dto.response.AssessmentResponse;
import com.mockmate.mockmate_backend.dto.response.ImprovementTipResponse;
import com.mockmate.mockmate_backend.dto.response.InterviewResponse;

public interface InterviewService {

	InterviewResponse generateQuestions(String clerkUserId, GenerateInterviewRequest request);

	AssessmentResponse submitAssessment(String clerkUserId, SubmitAssessmentRequest request);

	List<AssessmentResponse> getHistory(String clerkUserId);

	ImprovementTipResponse generateImprovementTip(String clerkUserId, ImprovementTipRequest request);
}