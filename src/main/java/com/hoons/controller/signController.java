package com.hoons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class signController {
	
	@GetMapping("join")
	public String join() {
		
		return "sign/join";
	}
	
}
