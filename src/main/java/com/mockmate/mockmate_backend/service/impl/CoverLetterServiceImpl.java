package com.mockmate.mockmate_backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockmate.ai.GeminiService;
import com.mockmate.ai.prompts.CoverLetterPromptBuilder;
import com.mockmate.mockmate_backend.dto.request.GenerateCoverLetterRequest;
import com.mockmate.mockmate_backend.dto.response.CoverLetterResponse;
import com.mockmate.mockmate_backend.entity.CoverLetter;
import com.mockmate.mockmate_backend.entity.User;
import com.mockmate.mockmate_backend.exception.AIServiceException;
import com.mockmate.mockmate_backend.exception.ResourceNotFoundException;
import com.mockmate.mockmate_backend.repository.CoverLetterRepository;
import com.mockmate.mockmate_backend.repository.UserRepository;
import com.mockmate.mockmate_backend.service.CoverLetterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoverLetterServiceImpl implements CoverLetterService {

	private final UserRepository userRepository;
	private final CoverLetterRepository coverLetterRepository;
	private final GeminiService geminiService;

	@Override
	public CoverLetterResponse generateCoverLetter(String clerkUserId, GenerateCoverLetterRequest request) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		String prompt = CoverLetterPromptBuilder.build(request.getCompanyName(), request.getJobTitle(),
				request.getJobDescription());

		String generatedContent;

		try {

			generatedContent = geminiService.generateContent(prompt);

		} catch (Exception ex) {

			throw new AIServiceException("Failed to generate cover letter");
		}

		CoverLetter coverLetter = CoverLetter.builder().companyName(request.getCompanyName())
				.jobTitle(request.getJobTitle()).jobDescription(request.getJobDescription()).content(generatedContent)
				.user(user).build();

		CoverLetter saved = coverLetterRepository.save(coverLetter);

		return mapToResponse(saved);
	}

	@Override
	public List<CoverLetterResponse> getHistory(String clerkUserId) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return coverLetterRepository.findByUserOrderByCreatedAtDesc(user).stream().map(this::mapToResponse).toList();
	}

	@Override
	public CoverLetterResponse getById(String clerkUserId, Long coverLetterId) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
				.orElseThrow(() -> new ResourceNotFoundException("Cover letter not found"));

		if (!coverLetter.getUser().getId().equals(user.getId())) {

			throw new ResourceNotFoundException("Cover letter not found");
		}

		return mapToResponse(coverLetter);
	}

	@Override
	public void deleteCoverLetter(String clerkUserId, Long coverLetterId) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
				.orElseThrow(() -> new ResourceNotFoundException("Cover letter not found"));

		if (!coverLetter.getUser().getId().equals(user.getId())) {

			throw new ResourceNotFoundException("Cover letter not found");
		}

		coverLetterRepository.delete(coverLetter);
	}

	private CoverLetterResponse mapToResponse(CoverLetter coverLetter) {

		return CoverLetterResponse.builder().id(coverLetter.getId()).companyName(coverLetter.getCompanyName())
				.jobTitle(coverLetter.getJobTitle()).jobDescription(coverLetter.getJobDescription())
				.content(coverLetter.getContent()).createdAt(coverLetter.getCreatedAt()).build();
	}
}