package com.hoons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hoons.domain.dto.goods.GoodsDetailDTO;
import com.hoons.service.GoodsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GoodsController {

	private final GoodsService goodsService;
	
	@GetMapping("goods/{no}")
	public String detail(@PathVariable(name = "no") Long no, Model model) {
		GoodsDetailDTO goods = goodsService.getDetail(no);
		model.addAttribute("goods", goods);
		return "goods/detail";
	}
}
