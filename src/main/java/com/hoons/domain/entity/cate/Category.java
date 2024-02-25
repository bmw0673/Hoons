package com.hoons.domain.entity.cate;

import java.util.List;

import com.hoons.domain.dto.cate.CateResponseDTO;
import com.hoons.domain.entity.goods.Goods;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "category")
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(nullable = false)
	private String name;
	
	
	//아래는 연관관계 매핑을 위한 구조
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "parent_id")
	private Category parent; //부모
	
	@OneToMany(mappedBy = "parent")
	private List<Category> child;
	
	@OneToMany(mappedBy = "category")
	private List<Goods> goods;
	
	/**
	 * 사용 : 카테고리 등록, 상품 등록 
	 * @return
	 */
	public CateResponseDTO toDto() {
		return CateResponseDTO.builder()
				.no(no)
				.name(name)
				.parent(parent==null?null:parent.toDto())
				.build();
	}
	
}
