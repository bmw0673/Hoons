package com.hoons.domain.entity.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	
	USER("일반유저"),
	ADMIN("관리자");
	
	private final String roleName;
}
