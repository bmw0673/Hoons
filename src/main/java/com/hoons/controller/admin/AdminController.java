package com.hoons.controller.admin;

import java.util.Map;

import org.springframework.stereotype.Controller;
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
@Controller
public class AdminController {

	private final S3FileUploadUtilV3 s3FileUploadUtilV3; 
	
	
	/**
	 * 사진 임시저장
	 * 저장할 사진 보여주기
	 * @param img
	 * @return
	 */
	@ResponseBody
	@PostMapping("/temp-upload")
	public Map<String, String> tempUpload(@RequestParam(name = "img") MultipartFile img) {
		return s3FileUploadUtilV3.s3TempUpload(img);
	}
}
