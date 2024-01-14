package com.hoons.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hoons.domain.dto.AddUserRequest;
import com.hoons.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "sign/login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "sign/signup";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/sign/login";
	}
	
	@PostMapping("/user")
	public String signup(AddUserRequest request){
		userService.save(request); //회원 가입 메서드 호출
		return "redirect:/sign/login"; //회원 가입이 완료된 이후 로그인 페이지 이동
	}
}
