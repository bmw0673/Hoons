package com.hoons.service.impl;

import org.springframework.stereotype.Service;

import com.hoons.domain.dto.board.NoticeDTO;
import com.hoons.domain.entity.board.NoticeRepogitory;
import com.hoons.service.BoardService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class BoardServiceProcess implements BoardService{

	private final NoticeRepogitory noticeRepogitory;

	@Override
	public void saveNotice(NoticeDTO dto) {
		noticeRepogitory.save(dto.toEntity());
	}
	
	
}
