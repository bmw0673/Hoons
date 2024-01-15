package com.hoons.service;

import org.springframework.stereotype.Service;

import com.hoons.domain.entity.RefreshToken;
import com.hoons.domain.entity.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshToken findByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken)
				.orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
	}
}
