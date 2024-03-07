package com.hoons.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.hoons.domain.dto.board.NoticeResponseDto;
import com.hoons.domain.dto.member.MemberSaveDTO;
import com.hoons.domain.entity.board.Notice;
import com.hoons.service.BoardService;
import com.hoons.service.MemberService;
import com.hoons.utils.page.PageRequestDTO;
import com.hoons.utils.page.PageResultDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/notices")
	public String notice(PageRequestDTO pageDto ,Model model) {
		
		PageResultDTO<NoticeResponseDto, Notice> list = boardService.getPageList(pageDto);
		model.addAttribute("list", list);
		
		return "board/notice";
	}
	
	@GetMapping("/notices/{no}")
	public String noticeDetail(@PathVariable(name = "no") Long no, Model model) {
		boardService.readNotice(no);
		NoticeResponseDto dto = boardService.getNotice(no);
		model.addAttribute("dto", dto);
		return "board/detail";
	}
	
}
