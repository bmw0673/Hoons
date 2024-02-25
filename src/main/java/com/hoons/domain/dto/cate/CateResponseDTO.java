package com.hoons.domain.dto.cate;

import java.time.LocalDateTime;

import com.hoons.domain.dto.board.NoticeResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CateResponseDTO {

	private Long no;
	private String name;
	private CateResponseDTO parent;
}
