package com.hoons.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoons.domain.dto.member.MemberSaveDTO;
import com.hoons.domain.entity.member.MemberRepository;
import com.hoons.domain.entity.member.Role;
import com.hoons.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceProcess implements MemberService {

	private final MemberRepository memberRepo;
	private final PasswordEncoder passEncoder;
	
	//회원가입
	@Transactional
	@Override
	public void save(MemberSaveDTO dto) {
		memberRepo.save(dto.toEntity(passEncoder)).addRole(Role.USER);
	}
}
