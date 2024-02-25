package com.hoons.service;

import java.util.List;

import com.hoons.domain.dto.cate.CateResponseDTO;
import com.hoons.domain.dto.cate.CateSaveDTO;

public interface CateService {

	void save(CateSaveDTO dto);

	List<CateResponseDTO> getList();

	List<CateResponseDTO> getChildList(Long parentNo);

}
