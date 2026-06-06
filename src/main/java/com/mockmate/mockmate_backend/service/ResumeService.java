package com.mockmate.mockmate_backend.service;

import com.mockmate.mockmate_backend.dto.request.CreateResumeRequest;
import com.mockmate.mockmate_backend.dto.request.ImproveResumeRequest;
import com.mockmate.mockmate_backend.dto.request.UpdateResumeRequest;
import com.mockmate.mockmate_backend.dto.response.ResumeResponse;

public interface ResumeService {

	ResumeResponse createResume(String clerkUserId, CreateResumeRequest request);

	ResumeResponse getResume(String clerkUserId);

	ResumeResponse updateResume(String clerkUserId, UpdateResumeRequest request);

	ResumeResponse improveResume(String clerkUserId, ImproveResumeRequest request);
}