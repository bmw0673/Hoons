package com.hoons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.hoons.domain.dto.member.MemberSaveDTO;
import com.hoons.service.MemberService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;
	
	// 회원가입
	@PostMapping("/members")
	public String join(MemberSaveDTO dto) {
			memberService.save(dto);
		return "redirect:/login";
	}
}
