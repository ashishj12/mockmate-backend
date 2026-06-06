package com.mockmate.mockmate_backend.util;

public final class SecurityUtils {

	private SecurityUtils() {
	}

//	public static String getCurrentClerkUserId() {
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();
//
//		return user.getClerkUserId();
//	}

	public static String getCurrentClerkUserId() {
		return "dev-user-123";
	}
}