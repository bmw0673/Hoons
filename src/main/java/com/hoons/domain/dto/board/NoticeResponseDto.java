package com.hoons.domain.dto.board;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoticeResponseDto {
	
	private Long no;
	private String title;
	private String content;
	private String writer;
	private int readCount;
	private LocalDateTime createdDateTime;
	
}
