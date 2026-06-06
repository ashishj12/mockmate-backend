package com.mockmate.mockmate_backend.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class ClerkJwtValidator {

	@Value("${clerk.jwt-secret}")
	private String jwtSecret;

	public Claims validate(String token) {

		SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}
}