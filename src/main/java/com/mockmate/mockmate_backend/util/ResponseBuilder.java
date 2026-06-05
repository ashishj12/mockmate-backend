package com.mockmate.mockmate_backend.util;

import java.time.LocalDateTime;

import com.mockmate.mockmate_backend.dto.response.ApiResponse;

public final class ResponseBuilder {

	private ResponseBuilder() {
	}

	public static <T> ApiResponse<T> success(String message, T data) {

		return ApiResponse.<T>builder().success(true).message(message).data(data).timestamp(LocalDateTime.now())
				.build();
	}
}