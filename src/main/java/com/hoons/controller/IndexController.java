package com.hoons.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hoons.domain.dto.goods.GoodsListDTO;
import com.hoons.service.GoodsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final GoodsService goodsService;
	
	@GetMapping("/join")
	public String join() {
		return "/sign/signup";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/sign/login";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		 List<GoodsListDTO> newList = goodsService.getNewList();
		 model.addAttribute("newList", newList);
		return "index";
	}
}
