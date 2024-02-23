package com.hoons.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hoons.domain.dto.cate.CateResponseDto;
import com.hoons.domain.dto.cate.CateSaveDto;
import com.hoons.service.BoardService;
import com.hoons.service.CateService;
import com.hoons.utils.S3FileUploadUtilV3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("admin")
@RequiredArgsConstructor
@Controller(value = "AdminCateController")
public class CateController {

	private final CateService cateService;
	
	@GetMapping("/cates")
	public String catePage(Model model) {
		
		List<CateResponseDto> list = cateService.getList();
		for(CateResponseDto dto: list) {
			System.out.println(dto);
		}
		
		model.addAttribute("list", list);
		
		return "admin/cate/cate";
	}
	
	@PostMapping("/cates")
	public String cates(CateSaveDto dto) {
		cateService.save(dto);
		
		return "redirect:/admin/cates";
	}
	
}
