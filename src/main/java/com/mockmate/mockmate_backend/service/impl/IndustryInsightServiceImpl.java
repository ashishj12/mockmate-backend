package com.mockmate.mockmate_backend.service.impl;

import org.springframework.stereotype.Service;

import com.mockmate.mockmate_backend.ai.prompts.IndustryInsightPromptBuilder;
import com.mockmate.mockmate_backend.dto.request.GenerateIndustryInsightRequest;
import com.mockmate.mockmate_backend.dto.response.IndustryInsightResponse;
import com.mockmate.mockmate_backend.entity.IndustryInsight;
import com.mockmate.mockmate_backend.entity.User;
import com.mockmate.mockmate_backend.exception.AIServiceException;
import com.mockmate.mockmate_backend.exception.ResourceNotFoundException;
import com.mockmate.mockmate_backend.repository.IndustryInsightRepository;
import com.mockmate.mockmate_backend.repository.UserRepository;
import com.mockmate.mockmate_backend.service.GeminiService;
import com.mockmate.mockmate_backend.service.IndustryInsightService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndustryInsightServiceImpl implements IndustryInsightService {

	private final UserRepository userRepository;
	private final IndustryInsightRepository insightRepository;
	private final GeminiService geminiService;

	@Override
	public IndustryInsightResponse generateInsight(String clerkUserId, GenerateIndustryInsightRequest request) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		String response;

		try {

			response = geminiService.generateContent(IndustryInsightPromptBuilder.build(request.getIndustry()));

		} catch (Exception ex) {

			throw new AIServiceException("Failed to generate industry insights");
		}

		IndustryInsight insight = IndustryInsight.builder().industry(request.getIndustry())

				// Temporary mapping
				.marketOutlook(response)

				.user(user).build();

		IndustryInsight saved = insightRepository.save(insight);

		return map(saved);
	}

	@Override
	public IndustryInsightResponse getInsight(String clerkUserId) {

		User user = userRepository.findByClerkUserId(clerkUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		IndustryInsight insight = insightRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Industry insight not found"));

		return map(insight);
	}

	private IndustryInsightResponse map(IndustryInsight insight) {

		return IndustryInsightResponse.builder().id(insight.getId()).industry(insight.getIndustry())
				.salaryRanges(insight.getSalaryRanges()).growthRate(insight.getGrowthRate())
				.demandLevel(insight.getDemandLevel()).topSkills(insight.getTopSkills())
				.marketOutlook(insight.getMarketOutlook()).keyTrends(insight.getKeyTrends())
				.recommendedSkills(insight.getRecommendedSkills()).build();
	}
}