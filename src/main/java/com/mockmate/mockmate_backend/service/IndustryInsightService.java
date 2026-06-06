package com.mockmate.mockmate_backend.service;

import com.mockmate.mockmate_backend.dto.request.GenerateIndustryInsightRequest;
import com.mockmate.mockmate_backend.dto.response.IndustryInsightResponse;

public interface IndustryInsightService {

	IndustryInsightResponse generateInsight(String clerkUserId, GenerateIndustryInsightRequest request);

	IndustryInsightResponse getInsight(String clerkUserId);
}
