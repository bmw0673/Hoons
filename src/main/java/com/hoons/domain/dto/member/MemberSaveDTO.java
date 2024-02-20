package com.hoons.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.hoons.domain.entity.member.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberSaveDTO {

	private String email;
	private String pass;
	
	public Member toEntity(PasswordEncoder passEncoder) {
		return Member.builder()
				.email(email)
				.password(passEncoder.encode(pass))
				.build();
	}
	
}
