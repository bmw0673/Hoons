package com.hoons.domain.dto.board;

import com.hoons.domain.entity.board.Notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class NoticeDTO {
	
	String title;
	
	String content;

	public Notice toEntity() {
		return Notice.builder()
				.title(title)
				.content(content)
				.build();
	}
	
}
