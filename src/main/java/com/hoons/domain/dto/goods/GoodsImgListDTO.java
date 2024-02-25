package com.hoons.domain.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoodsImgListDTO {
	private boolean thumbnail;
	private String url;
	private String newName;
}
