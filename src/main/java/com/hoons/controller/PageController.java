package com.hoons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PageController {

	@GetMapping("/join")
	public String join() {
		return "/sign/signup";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/sign/login";
	}

}
