package com.mockmate.mockmate_backend.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mockmate.mockmate_backend.dto.response.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().success(false)
				.message(ex.getMessage()).errors(List.of()).timestamp(LocalDateTime.now()).build());
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {

		return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.builder().success(false)
				.message(ex.getMessage()).errors(List.of()).timestamp(LocalDateTime.now()).build());
	}

	@ExceptionHandler(AIServiceException.class)
	public ResponseEntity<ErrorResponse> handleAI(AIServiceException ex) {

		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ErrorResponse.builder().success(false)
				.message(ex.getMessage()).errors(List.of()).timestamp(LocalDateTime.now()).build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().success(false)
				.message("Validation failed").errors(errors).timestamp(LocalDateTime.now()).build());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {

		List<String> errors = ex.getConstraintViolations().stream()
				.map(v -> v.getPropertyPath() + ": " + v.getMessage()).toList();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().success(false)
				.message("Validation failed").errors(errors).timestamp(LocalDateTime.now()).build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorResponse.builder().success(false).message("Internal server error")
						.errors(List.of(ex.getMessage())).timestamp(LocalDateTime.now()).build());
	}
}