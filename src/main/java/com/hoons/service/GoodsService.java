package com.hoons.service;

import java.util.List;

import org.springframework.ui.Model;

import com.hoons.domain.dto.goods.GoodsDetailDTO;
import com.hoons.domain.dto.goods.GoodsImgSaveDTO;
import com.hoons.domain.dto.goods.GoodsListDTO;
import com.hoons.domain.dto.goods.GoodsSaveDTO;
import com.hoons.domain.entity.goods.Goods;
import com.hoons.utils.page.PageRequestDTO;
import com.hoons.utils.page.PageResultDTO;

public interface GoodsService {

	// 관리자 상품 등록
	void saveProcess(GoodsSaveDTO goodsDTO, GoodsImgSaveDTO goodsImgDTO);

	// 관리자 상품 리스트
	PageResultDTO<GoodsListDTO, Goods> getList(PageRequestDTO dto);

	// 상품 상세
	GoodsDetailDTO getDetail(Long goodsId);

	// new arrivals
	List<GoodsListDTO> getNewList();

	// 상품 삭제
	void delete(Long no);

	// 상품 수정
	void update(Long no, GoodsSaveDTO goods, GoodsImgSaveDTO goodsImg);

}
