package com.hoons.domain.dto.cate;

import com.hoons.domain.dto.board.NoticeDTO;
import com.hoons.domain.entity.cate.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CateSaveDto {

	private Long parentId;
	private String cateName;
	
	public Category toEntity(Category parent) {
		return Category.builder()
				.parent(parent)
				.name(cateName)
				.build();
	}
}
