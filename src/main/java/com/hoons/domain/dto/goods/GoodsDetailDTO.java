package com.hoons.domain.dto.goods;

import java.time.LocalDateTime;
import java.util.List;

import com.hoons.domain.dto.cate.CateResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailDTO {
	
	private Long no;
	private String name;
	private long price;
	private int stockQuantity;
	private String content;
	private String cateName;
	private LocalDateTime updatedDate;
	private LocalDateTime createdDate;
	
	private List<GoodsImgListDTO> imgs;
	private CateResponseDTO cateDTO;
}
