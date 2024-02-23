package com.hoons.service;

import java.util.List;

import com.hoons.domain.dto.board.NoticeDTO;
import com.hoons.domain.dto.board.NoticeResponseDto;
import com.hoons.domain.entity.board.Notice;
import com.hoons.utils.page.PageRequestDTO;
import com.hoons.utils.page.PageResultDTO;

public interface BoardService {
	
	void saveNotice(NoticeDTO dto);

	PageResultDTO<NoticeResponseDto, Notice> getPageList(PageRequestDTO pageDto);

	NoticeResponseDto getNotice(Long no);

	void readNotice(Long no);
}
