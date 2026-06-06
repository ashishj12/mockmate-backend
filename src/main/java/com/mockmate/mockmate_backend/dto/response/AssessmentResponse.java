package com.mockmate.mockmate_backend.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssessmentResponse {

	private Long id;

	private Integer score;

	private String questions;

	private String answers;

	private String improvementTip;

	private LocalDateTime createdAt;
}