package com.hoons.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class RefreshToken {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "refreshToken_id", updatable = false)
	@Id
	private Long id;
	
	@Column(name = "user_id", nullable = false, unique = true)
	private Long userId;
	
	@Column(name = "refresh_token", nullable = false)
	private String refreshToken;
	
	public RefreshToken(Long userId, String refreshToken) {
		this.userId = userId;
		this.refreshToken = refreshToken;
	}
	
	public RefreshToken update(String newRefreshToken) {
		this.refreshToken = newRefreshToken;
		return this;
	}
	
}
