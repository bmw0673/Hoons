package com.hoons.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.hoons.config.jwt.TokenProvider;
import com.hoons.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        Member user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}