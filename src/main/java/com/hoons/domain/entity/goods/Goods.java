package com.hoons.domain.entity.goods;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hoons.domain.dto.goods.GoodsDetailDTO;
import com.hoons.domain.dto.goods.GoodsListDTO;
import com.hoons.domain.dto.goods.GoodsSaveDTO;
import com.hoons.domain.entity.cate.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goods")
@Entity
public class Goods {
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private long price;
	
	@Column(nullable = false)
	private int stockQuantity;
	
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updatedDateTime;
	
	
	@Column(nullable = false, columnDefinition = "longtext")
	private String content;
	
	@OneToMany(mappedBy = "goods", cascade = CascadeType.REMOVE)
	private List<GoodsImg> imgs;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	
	public GoodsListDTO toListDTO() {
		return GoodsListDTO.builder()
				.no(no)
				.name(name)
				.price(price)
				.stockQuantity(stockQuantity)
				.createdDate(createdDateTime)
				.updatedDate(updatedDateTime)
				.imgs(getImgs().stream().filter(i -> i.isThumbnail())
						.map(GoodsImg::toListDTO)
						.collect(Collectors.toList()))
				.cateDTO(category.toDto())
				.build();
	}
	

	public GoodsDetailDTO toDetailDTO() {
		return  GoodsDetailDTO.builder()
				.no(no)
				.name(name)
				.price(price)
				.stockQuantity(stockQuantity)
				.content(content)
				.cateDTO(category.toDto())
				.imgs(imgs.stream()
						.map(GoodsImg::toListDTO)
						.collect(Collectors.toList()))
				.createdDate(createdDateTime)
				.updatedDate(updatedDateTime)
				.build();
	}


	public void update(GoodsSaveDTO goodsDTO, Category cate) {
		this.category = cate;
		this.content = goodsDTO.getContent();
		this.name = goodsDTO.getName();
		this.price = goodsDTO.getPrice();
		this.stockQuantity = goodsDTO.getStockQuantity();
	}
}
