package com.mockmate.mockmate_backend.service;

import java.util.List;

import com.mockmate.mockmate_backend.dto.request.GenerateCoverLetterRequest;
import com.mockmate.mockmate_backend.dto.response.CoverLetterResponse;

public interface CoverLetterService {

	CoverLetterResponse generateCoverLetter(String clerkUserId, GenerateCoverLetterRequest request);

	List<CoverLetterResponse> getHistory(String clerkUserId);

	CoverLetterResponse getById(String clerkUserId, Long coverLetterId);

	void deleteCoverLetter(String clerkUserId, Long coverLetterId);
}
