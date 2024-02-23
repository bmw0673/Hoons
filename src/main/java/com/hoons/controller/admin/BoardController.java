package com.hoons.controller.admin;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hoons.domain.dto.board.NoticeDTO;
import com.hoons.service.BoardService;
import com.hoons.utils.S3FileUploadUtilV3;

import lombok.RequiredArgsConstructor;

@RequestMapping("admin")
@RequiredArgsConstructor
@Controller(value = "AdminBoardController")
public class BoardController {

	private final S3FileUploadUtilV3 s3FileUploadUtilV3;
	private final BoardService boardService; 
	
	@GetMapping("/notices")
	public String notice() {
		return "admin/board/notice";
	}
	
	@PostMapping("/notices")
	public String notice(NoticeDTO dto) {
		boardService.saveNotice(dto);
		return "redirect:/admin/notices";
	}
	
	@ResponseBody
	@PostMapping("/temp-upload")
	public Map<String, String> tempUpload(@RequestParam(name = "img") MultipartFile img) {
		return s3FileUploadUtilV3.s3TempUpload(img);
	}
}
