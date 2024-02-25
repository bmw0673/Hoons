package com.hoons.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoons.domain.dto.cate.CateResponseDTO;
import com.hoons.domain.dto.goods.GoodsDetailDTO;
import com.hoons.domain.dto.goods.GoodsImgSaveDTO;
import com.hoons.domain.dto.goods.GoodsListDTO;
import com.hoons.domain.dto.goods.GoodsSaveDTO;
import com.hoons.domain.entity.goods.Goods;
import com.hoons.service.CateService;
import com.hoons.service.GoodsService;
import com.hoons.utils.page.PageRequestDTO;
import com.hoons.utils.page.PageResultDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("admin")
@Controller(value = "adminGoodsController")
public class GoodsController {
	
	private final GoodsService goodsService;
	
	private final CateService cateService;
	
	@GetMapping("/goods")
	public String goods(Model model) {
		
		List<CateResponseDTO> cateList = cateService.getList();
		model.addAttribute("cateList", cateList);
		
		return "admin/goods/register";
	}
	
	/*
	 * 상품리스트 페이지
	 */
	@GetMapping("/goods/list")
	public String goodsList(Model model, PageRequestDTO dto) {
		PageResultDTO<GoodsListDTO, Goods> result = goodsService.getList(dto); 
		model.addAttribute("result", result);
		return "admin/goods/list";
	}
	
	/*
	 * 상품상세 페이지
	 */
	@GetMapping("/goods/{goodsId}")
	public String detail(@PathVariable(name = "goodsId") Long goodsId, Model model) {
		
		List<CateResponseDTO> cateList = cateService.getList();
		model.addAttribute("cateList", cateList);
		
		GoodsDetailDTO dto = goodsService.getDetail(goodsId);
		model.addAttribute("goods", dto);
		
		return "admin/goods/detail";
	}
	
	/*
	 * 1.상품저장 기능
	 * 2.상품테이블과 상품이미지 테이블에 저장
	 * 3.임시저장소에 저장된 사진은 삭제하고 주소를 옮김
	 */
	@PostMapping("/goods")
	public String save(GoodsSaveDTO goods, GoodsImgSaveDTO goodsImg) {
		goodsService.saveProcess(goods, goodsImg);
		return "redirect:/admin/goods/list";
	}
	
	@DeleteMapping("/goods/{no}")
	public String delete(@PathVariable(name = "no") Long no) {
		goodsService.delete(no);
		return "redirect:/admin/goods/list";
	}
	
	@PutMapping("/goods/{no}")
	public String update(@PathVariable(name = "no") Long no, GoodsSaveDTO goods, GoodsImgSaveDTO goodsImg ) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>수정 서비스");
		goodsService.update(no, goods, goodsImg);
		return "redirect:/admin/goods/"+no;
	}
}
