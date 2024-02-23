package com.hoons.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admin")
@Controller
public class AdminPageController {

	@GetMapping("")
	public String admin() {
		return "admin/dashBoard";
	}
	
	@GetMapping("/goods")
	public String goods() {
		return "admin/goods/register";
	}
	
	@GetMapping("/goods/list")
	public String goodsList() {
		return "admin/goods/list";
	}
	
}
