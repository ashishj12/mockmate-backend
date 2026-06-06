package com.mockmate.mockmate_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResumeResponse {

	private Long id;

	private String content;
}