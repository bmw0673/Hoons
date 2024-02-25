package com.hoons.domain.dto.goods;

import com.hoons.domain.entity.cate.Category;
import com.hoons.domain.entity.goods.Goods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class GoodsSaveDTO {
	
	private String name;
	private long price;
	private int stockQuantity;
	private String content;
	private Long cateNo;
	
	public Goods toEntity(Category cate) {
		return Goods.builder()
				.name(name)
				.price(price)
				.content(content)
				.stockQuantity(stockQuantity)
				.category(cate)
				.build();
	}
	
}