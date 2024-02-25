package com.hoons.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hoons.domain.dto.cate.CateResponseDTO;
import com.hoons.domain.dto.cate.CateSaveDTO;
import com.hoons.service.BoardService;
import com.hoons.service.CateService;
import com.hoons.utils.S3FileUploadUtilV3;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("admin")
@RequiredArgsConstructor
@Controller(value = "AdminCateController")
public class CateController {

	private final CateService cateService;
	
	@GetMapping("/cates")
	public String catePage(Model model) {
		
		List<CateResponseDTO> list = cateService.getList();
		for(CateResponseDTO dto: list) {
			System.out.println(dto);
		}
		
		model.addAttribute("list", list);
		
		return "admin/cate/cate";
	}
	
	@ResponseBody
	@GetMapping("/cates/{parentNo}")
	public List<CateResponseDTO> getchild(@PathVariable(name = "parentNo") Long parentNo){
		return cateService.getChildList(parentNo);
	}
	
	@PostMapping("/cates")
	public String cates(CateSaveDTO dto) {
		cateService.save(dto);
		
		return "redirect:/admin/cates";
	}
	
}
