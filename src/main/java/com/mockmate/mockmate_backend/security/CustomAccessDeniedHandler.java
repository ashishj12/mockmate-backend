package com.mockmate.mockmate_backend.security;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockmate.mockmate_backend.dto.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.access.AccessDeniedException ex) throws java.io.IOException {

		response.setStatus(403);
		response.setContentType("application/json");

		ErrorResponse error = ErrorResponse.builder().success(false).message("Forbidden").errors(List.of())
				.timestamp(LocalDateTime.now()).build();

		new ObjectMapper().writeValue(response.getOutputStream(), error);
	}
}