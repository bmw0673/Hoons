package com.hoons.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
	
	Optional<RefreshToken> findByuserId(Long userId);
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
