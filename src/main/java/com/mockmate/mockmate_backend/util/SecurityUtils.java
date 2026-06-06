package com.mockmate.mockmate_backend.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mockmate.mockmate_backend.security.AuthenticatedUser;

public final class SecurityUtils {

	private SecurityUtils() {
	}

	public static String getCurrentClerkUserId() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();

		return user.getClerkUserId();
	}
}