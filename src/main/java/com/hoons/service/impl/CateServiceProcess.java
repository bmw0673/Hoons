package com.hoons.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hoons.domain.dto.cate.CateResponseDto;
import com.hoons.domain.dto.cate.CateSaveDto;
import com.hoons.domain.entity.cate.CateRepogitory;
import com.hoons.domain.entity.cate.Category;
import com.hoons.service.CateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CateServiceProcess implements CateService{

	private final CateRepogitory cateRepo;
	
	@Override
	public void save(CateSaveDto dto) {
		
		if(dto.getParentId()==null) {
			cateRepo.save(dto.toEntity(null));
		}else {
			Category parent = cateRepo.findById(dto.getParentId()).orElseThrow();
			cateRepo.save(dto.toEntity(parent));
		}
		
	}

	/**
	 * 카테고리 등록
	 * 부모가 null 값만 가져옵니다.
	 */
	@Override
	public List<CateResponseDto> getList() {
		Sort sort = Sort.by("no").descending();
		return cateRepo.findAll(sort).stream()
		.map(Category::toDto)
		.collect(Collectors.toList());
	}

}
