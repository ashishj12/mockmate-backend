package com.mockmate.mockmate_backend.service;

import com.mockmate.mockmate_backend.dto.request.AtsAnalysisRequest;
import com.mockmate.mockmate_backend.dto.response.AtsAnalysisResponse;

public interface AtsService {

	AtsAnalysisResponse analyzeResume(String clerkUserId, AtsAnalysisRequest request);

	AtsAnalysisResponse getAnalysis(String clerkUserId);
}
