package com.mockmate.mockmate_backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockmate.ai.GeminiService;
import com.mockmate.ai.prompts.InterviewPromptBuilder;
import com.mockmate.mockmate_backend.dto.request.GenerateInterviewRequest;
import com.mockmate.mockmate_backend.dto.request.ImprovementTipRequest;
import com.mockmate.mockmate_backend.dto.request.SubmitAssessmentRequest;
import com.mockmate.mockmate_backend.dto.response.AssessmentResponse;
import com.mockmate.mockmate_backend.dto.response.ImprovementTipResponse;
import com.mockmate.mockmate_backend.dto.response.InterviewResponse;
import com.mockmate.mockmate_backend.entity.Assessment;
import com.mockmate.mockmate_backend.exception.AIServiceException;
import com.mockmate.mockmate_backend.exception.ResourceNotFoundException;
import com.mockmate.mockmate_backend.repository.AssessmentRepository;
import com.mockmate.mockmate_backend.repository.UserRepository;
import com.mockmate.mockmate_backend.service.InterviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

	private final UserRepository userRepository;
	private final AssessmentRepository assessmentRepository;
	private final GeminiService geminiService;

	@Override
	public InterviewResponse generateQuestions(String clerkUserId, GenerateInterviewRequest request) {

		userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		try {

			String questions = geminiService.generateContent(InterviewPromptBuilder.buildQuestions(request.getRole(),
					request.getLevel(), request.getTechnology()));

			return InterviewResponse.builder().questions(questions).build();

		} catch (Exception ex) {

			throw new AIServiceException("Failed to generate interview questions");
		}
	}

	@Override
	public AssessmentResponse submitAssessment(String clerkUserId, SubmitAssessmentRequest request) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Assessment assessment = Assessment.builder().questions(request.getQuestions()).answers(request.getAnswers())
				.quizScore(request.getScore()).user(user).build();

		Assessment saved = assessmentRepository.save(assessment);

		return map(saved);
	}

	@Override
	public List<AssessmentResponse> getHistory(String clerkUserId) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return assessmentRepository.findByUserOrderByCreatedAtDesc(user).stream().map(this::map).toList();
	}

	@Override
	public ImprovementTipResponse generateImprovementTip(String clerkUserId, ImprovementTipRequest request) {

		userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		try {

			String tip = geminiService
					.generateContent(InterviewPromptBuilder.buildImprovementTips(request.getAnswers()));

			return ImprovementTipResponse.builder().improvementTip(tip).build();

		} catch (Exception ex) {

			throw new AIServiceException("Failed to generate improvement tips");
		}
	}

	private AssessmentResponse map(Assessment assessment) {

		return AssessmentResponse.builder().id(assessment.getId()).questions(assessment.getQuestions())
				.answers(assessment.getAnswers()).score(assessment.getQuizScore()).createdAt(assessment.getCreatedAt())
				.build();
	}
}