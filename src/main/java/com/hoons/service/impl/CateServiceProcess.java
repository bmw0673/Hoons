package com.hoons.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hoons.domain.dto.cate.CateResponseDTO;
import com.hoons.domain.dto.cate.CateSaveDTO;
import com.hoons.domain.entity.cate.CateRepogitory;
import com.hoons.domain.entity.cate.Category;
import com.hoons.service.CateService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CateServiceProcess implements CateService{

	private final CateRepogitory cateRepo;
	
	@Override
	public void save(CateSaveDTO dto) {
		
		if(dto.getParentId()==null) {
			cateRepo.save(dto.toEntity(null));
		}else {
			Category parent = cateRepo.findById(dto.getParentId()).orElseThrow();
			cateRepo.save(dto.toEntity(parent));
		}
		
	}

	/**
	 * 카테고리 등록 불러오기
	 */
	@Override
	public List<CateResponseDTO> getList() {
		Sort sort = Sort.by("no").descending();
		return cateRepo.findAll(sort).stream()
		.map(Category::toDto)
		.collect(Collectors.toList());
	}

	/**
	 * 자식 카테고리 불러오기
	 */
	@Transactional
	@Override
	public List<CateResponseDTO> getChildList(Long parentNo) {
		Category parent = cateRepo.findById(parentNo).orElseThrow(); 
		return cateRepo.findByParent(parent).stream()
				.map(Category::toDto)
				.collect(Collectors.toList());
	}

}
