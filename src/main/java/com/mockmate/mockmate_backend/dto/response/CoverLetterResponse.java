package com.mockmate.mockmate_backend.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoverLetterResponse {

	private Long id;

	private String companyName;

	private String jobTitle;

	private String jobDescription;

	private String content;

	private LocalDateTime createdAt;
}