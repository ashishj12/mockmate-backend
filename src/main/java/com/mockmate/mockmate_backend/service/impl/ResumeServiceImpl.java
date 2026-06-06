package com.mockmate.mockmate_backend.service.impl;

import org.springframework.stereotype.Service;

import com.mockmate.mockmate_backend.dto.request.CreateResumeRequest;
import com.mockmate.mockmate_backend.dto.request.ImproveResumeRequest;
import com.mockmate.mockmate_backend.dto.request.UpdateResumeRequest;
import com.mockmate.mockmate_backend.dto.response.ResumeResponse;
import com.mockmate.mockmate_backend.entity.Resume;
import com.mockmate.mockmate_backend.entity.User;
import com.mockmate.mockmate_backend.exception.BusinessException;
import com.mockmate.mockmate_backend.exception.ResourceNotFoundException;
import com.mockmate.mockmate_backend.repository.ResumeRepository;
import com.mockmate.mockmate_backend.repository.UserRepository;
import com.mockmate.mockmate_backend.service.ResumeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

	private final ResumeRepository resumeRepository;
	private final UserRepository userRepository;

	@Override
	public ResumeResponse createResume(String clerkUserId, CreateResumeRequest request) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		if (resumeRepository.existsByUser(user)) {

			throw new BusinessException("Resume already exists");
		}

		Resume resume = Resume.builder().content(request.getContent()).user(user).build();

		Resume saved = resumeRepository.save(resume);

		return mapToResponse(saved);
	}

	@Override
	public ResumeResponse getResume(String clerkUserId) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Resume resume = resumeRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

		return mapToResponse(resume);
	}

	@Override
	public ResumeResponse updateResume(String clerkUserId, UpdateResumeRequest request) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Resume resume = resumeRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

		resume.setContent(request.getContent());

		Resume updated = resumeRepository.save(resume);

		return mapToResponse(updated);
	}

	@Override
	public ResumeResponse improveResume(String clerkUserId, ImproveResumeRequest request) {

		throw new UnsupportedOperationException("Implemented in Gemini phase");
	}

	private ResumeResponse mapToResponse(Resume resume) {

		return ResumeResponse.builder().id(resume.getId()).content(resume.getContent()).build();
	}
}