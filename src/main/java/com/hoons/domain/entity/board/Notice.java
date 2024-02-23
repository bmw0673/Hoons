package com.hoons.domain.entity.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.hoons.domain.dto.board.NoticeResponseDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice")
@Entity
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "VARCHAR(255) DEFAULT '관리자'")
	private String writer;
	
	@Column(columnDefinition = "longtext")
	private String content;

	@ColumnDefault("0") //default 0
	private int readCount;
	
	// 작성 날짜
	@CreationTimestamp
	private LocalDateTime createdDateTime;

	public NoticeResponseDto toResponseDto() {
		return NoticeResponseDto.builder()
				.no(no)
				.title(title)
				.content(content)
				.writer(writer)
				.readCount(readCount)
				.createdDateTime(createdDateTime)
				.build();
	}
	
	public void read() {
		this.readCount = readCount+1; 
	}
}
