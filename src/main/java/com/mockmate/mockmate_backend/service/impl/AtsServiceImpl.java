package com.mockmate.mockmate_backend.service.impl;

import org.springframework.stereotype.Service;

import com.mockmate.ai.GeminiService;
import com.mockmate.ai.prompts.AtsPromptBuilder;
import com.mockmate.mockmate_backend.dto.request.AtsAnalysisRequest;
import com.mockmate.mockmate_backend.dto.response.AtsAnalysisResponse;
import com.mockmate.mockmate_backend.entity.AtsAnalysis;
import com.mockmate.mockmate_backend.entity.Resume;
import com.mockmate.mockmate_backend.entity.User;
import com.mockmate.mockmate_backend.exception.AIServiceException;
import com.mockmate.mockmate_backend.exception.ResourceNotFoundException;
import com.mockmate.mockmate_backend.repository.AtsAnalysisRepository;
import com.mockmate.mockmate_backend.repository.ResumeRepository;
import com.mockmate.mockmate_backend.repository.UserRepository;
import com.mockmate.mockmate_backend.service.AtsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtsServiceImpl implements AtsService {

	private final UserRepository userRepository;
	private final ResumeRepository resumeRepository;
	private final AtsAnalysisRepository atsRepository;
	private final GeminiService geminiService;

	@Override
	public AtsAnalysisResponse analyzeResume(String clerkUserId, AtsAnalysisRequest request) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Resume resume = resumeRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

		String prompt = AtsPromptBuilder.build(resume.getContent(), request.getJobDescription());

		String response;

		try {

			response = geminiService.generateContent(prompt);

		} catch (Exception ex) {

			throw new AIServiceException("Failed to analyze resume");
		}

		AtsAnalysis analysis = AtsAnalysis.builder().rawResponse(response).resume(resume).build();

		AtsAnalysis saved = atsRepository.save(analysis);

		return AtsAnalysisResponse.builder().id(saved.getId()).rawResponse(response).build();
	}

	@Override
	public AtsAnalysisResponse getAnalysis(String clerkUserId) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Resume resume = resumeRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

		AtsAnalysis analysis = atsRepository.findByResume(resume)
				.orElseThrow(() -> new ResourceNotFoundException("ATS analysis not found"));

		return AtsAnalysisResponse.builder().id(analysis.getId()).rawResponse(analysis.getRawResponse()).build();
	}
}