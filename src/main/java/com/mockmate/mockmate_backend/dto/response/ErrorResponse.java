package com.mockmate.mockmate_backend.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

	private final boolean success;

	private final String message;

	private final List<String> errors;

	private final LocalDateTime timestamp;
}