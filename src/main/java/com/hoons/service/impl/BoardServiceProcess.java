package com.hoons.service.impl;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hoons.domain.dto.board.NoticeDTO;
import com.hoons.domain.dto.board.NoticeResponseDto;
import com.hoons.domain.entity.board.Notice;
import com.hoons.domain.entity.board.NoticeRepogitory;
import com.hoons.service.BoardService;
import com.hoons.utils.page.PageRequestDTO;
import com.hoons.utils.page.PageResultDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceProcess implements BoardService {

	private final NoticeRepogitory noticeRepo;

	@Override
	public void saveNotice(NoticeDTO dto) {
		noticeRepo.save(dto.toEntity());
	}

	@Override
	public PageResultDTO<NoticeResponseDto, Notice> getPageList(PageRequestDTO pageDto) {
		
		Pageable pageable = pageDto.getPageable(Sort.by("no").descending());

		Page<Notice> result = noticeRepo.findAll(pageable);

		Function<Notice, NoticeResponseDto> fn = entity -> entity.toResponseDto();

		return new PageResultDTO<>(result, fn);

	}

	@Override
	public NoticeResponseDto getNotice(Long no) {
		return noticeRepo.findById(no).orElseThrow().toResponseDto();
	}

	@Transactional
	@Override
	public void readNotice(Long no) {
		 Notice notice = noticeRepo.findById(no).orElseThrow();
		 notice.read();
		 noticeRepo.save(notice);
		 
	}

}
