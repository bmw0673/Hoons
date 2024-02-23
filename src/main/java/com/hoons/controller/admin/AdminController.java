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

}
