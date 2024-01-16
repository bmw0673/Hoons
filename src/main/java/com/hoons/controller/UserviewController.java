package com.hoons.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hoons.domain.dto.UserDto;
import com.hoons.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserviewController {

	private final UserService userService;
	
	@GetMapping("/join")
	public String signupPage() {
		return "/sign/signup";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "/sign/login";
	}
	
	//UserDto를 파라미터로 받아서 회원가입 메서드를 호출
    @PostMapping("/signup")
    public String signup(UserDto userDto) {
    	System.out.println("컨트롤러 회원가입실행");
    	userService.signup(userDto);
    	return "redirect:/login";
    }
}
