package com.mockmate.mockmate_backend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticatedUser {

	private String clerkUserId;

	private String email;
}