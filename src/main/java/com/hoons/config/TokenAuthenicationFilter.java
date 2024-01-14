package com.hoons.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.hoons.config.jwt.TokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenAuthenicationFilter extends OncePerRequestFilter{

	private final TokenProvider tokenProvider;

	private final static String HEADER_AUTHORIZATION = "Authorization";
	private final static String TOKEN_FREFIX = "Bearer";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//요청 헤더의 Autjorization 키의 값 조회
	}
}
