package com.hoons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PageController {

	@GetMapping("/notices")
	public String notice() {
		return "/board/notice";
	}
	
	@GetMapping("/notices/{no}")
	public String noticeDetail(@PathVariable(name = "no") int no) {
		return "/board/detail";
	}
	
	@GetMapping("/join")
	public String join() {
		return "/sign/signup";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/sign/login";
	}

}
