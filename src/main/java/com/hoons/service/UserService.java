package com.hoons.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoons.domain.dto.AddUserRequest;
import com.hoons.domain.entity.Member;
import com.hoons.domain.entity.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Long save(AddUserRequest dto) {
		return memberRepository.save(Member.builder()
				.email(dto.getEmail())
				.password(bCryptPasswordEncoder.encode(dto.getPassword()))
				.build()).getId();
	}
	
	public Member findById(Long userId) {
        return memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
	
	public Member findByEmail(String email) {
	    return memberRepository.findByEmail(email)
	            .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
	}
}
