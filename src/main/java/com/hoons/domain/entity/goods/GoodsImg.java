package com.hoons.domain.entity.goods;

import com.hoons.domain.dto.goods.GoodsImgListDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goodsImg")
@Entity
public class GoodsImg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(nullable = false)
	private String newName;
	
	private boolean thumbnail; //대표이미지 여부
	
	@Column(nullable = false)
	private String url;
	
	@JoinColumn(name = "goods_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Goods goods;
	
	public GoodsImgListDTO toListDTO() {
		return GoodsImgListDTO.builder()
				.url(url)
				.thumbnail(thumbnail)
				.newName(newName)
				.build();
	}
}
