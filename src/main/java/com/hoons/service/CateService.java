package com.hoons.service;

import java.util.List;

import com.hoons.domain.dto.cate.CateResponseDto;
import com.hoons.domain.dto.cate.CateSaveDto;

public interface CateService {

	void save(CateSaveDto dto);

	List<CateResponseDto> getList();

}
