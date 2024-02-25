package com.hoons.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hoons.domain.dto.goods.GoodsDetailDTO;
import com.hoons.domain.dto.goods.GoodsImgSaveDTO;
import com.hoons.domain.dto.goods.GoodsListDTO;
import com.hoons.domain.dto.goods.GoodsSaveDTO;
import com.hoons.domain.entity.cate.CateRepogitory;
import com.hoons.domain.entity.cate.Category;
import com.hoons.domain.entity.goods.Goods;
import com.hoons.domain.entity.goods.GoodsImg;
import com.hoons.domain.entity.goods.GoodsImgRepogitory;
import com.hoons.domain.entity.goods.GoodsRepogitory;
import com.hoons.service.GoodsService;
import com.hoons.utils.S3FileUploadUtilV3;
import com.hoons.utils.page.PageRequestDTO;
import com.hoons.utils.page.PageResultDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GoodsServiceProcess implements GoodsService {

	private final S3FileUploadUtilV3 s3FileUploadUtilV3;

	private final GoodsRepogitory goodsRepo;

	private final CateRepogitory cateRepo;

	private final GoodsImgRepogitory goodsImgRepo;

	/**
	 * 상품 등록
	 */
	@Override
	public void saveProcess(GoodsSaveDTO goodsDTO, GoodsImgSaveDTO goodsImgDTO) {

		Category cate = cateRepo.findById(goodsDTO.getCateNo()).orElseThrow();

		// 1.상품저장
		Goods goods = goodsRepo.save(goodsDTO.toEntity(cate));

		int size = goodsImgDTO.getNewName().length;

		for (int i = 0; i < size; i++) {

			// 2.s3이동
			String url = s3FileUploadUtilV3.s3TempToSrc(goodsImgDTO.getNewName()[i]);

			// 3.이미지저장
			if (i == 0) {
				goodsImgRepo.save(GoodsImg.builder().newName(goodsImgDTO.getNewName()[i]).url(url).goods(goods)
						.thumbnail(true).build());
			} else {
				goodsImgRepo.save(GoodsImg.builder().newName(goodsImgDTO.getNewName()[i]).url(url).goods(goods)
						.thumbnail(false).build());
			}
		}
	}

	/**
	 * 관리자 상품 리스트 :: 페이징
	 */
	@Transactional
	@Override
	public PageResultDTO<GoodsListDTO, Goods> getList(PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("no").descending());

		Page<Goods> result = goodsRepo.findAll(pageable);

		Function<Goods, GoodsListDTO> fn = entity -> entity.toListDTO();

		return new PageResultDTO<>(result, fn);
	}

	/**
	 * 상품 상세 페이지
	 */
	@Transactional
	@Override
	public GoodsDetailDTO getDetail(Long goodsId) {
		Goods goods = goodsRepo.findById(goodsId).orElseThrow();
		return goods.toDetailDTO();
	}

	/**
	 * 인덱스 페이지 신상품 리스트
	 */
	@Transactional
	@Override
	public List<GoodsListDTO> getNewList() {
		Pageable pageable = PageRequest.of(0, 12, Direction.DESC, "createdDateTime");
		return goodsRepo.findAll(pageable).getContent().stream()
				.map(Goods::toListDTO)
				.collect(Collectors.toList());
	}

	/**
	 * 상품 삭제
	 */
	@Transactional
	@Override
	public void delete(Long no) {
		Goods goods = goodsRepo.findById(no).orElseThrow();
		
		// s3 버킷 이미지 파일 삭제
		goods.getImgs().stream()
			.peek(i -> s3FileUploadUtilV3.s3Delete(i.getNewName()))
			.collect(Collectors.toList());
		
		goodsRepo.delete(goods);
	}

	/**
	 * 상품 수정
	 */
	@Transactional
	@Override
	public void update(Long no, GoodsSaveDTO goodsDTO, GoodsImgSaveDTO goodsImgDTO) {
		System.out.println(goodsDTO);
		System.out.println(goodsImgDTO);
		
		Category cate = cateRepo.findById(goodsDTO.getCateNo()).orElseThrow();

		
		Goods goods = goodsRepo.findById(no).orElseThrow();
		goods.update(goodsDTO, cate);
		goodsRepo.save(goods);
		
		int size = goodsImgDTO.getNewName().length;
		for (int i = 0; i < size; i++) {

			//기존에 존재하는 이미지
			if(goodsImgRepo.existsByNewName(goodsImgDTO.getNewName()[i])) {
				continue;
			}
			// 2.s3이동
			String url = s3FileUploadUtilV3.s3TempToSrc(goodsImgDTO.getNewName()[i]);
			
			// 3.이미지저장
			if (i == 0) {
				goodsImgRepo.save(GoodsImg.builder().newName(goodsImgDTO.getNewName()[i]).url(url).goods(goods)
						.thumbnail(true).build());
			} else {
				goodsImgRepo.save(GoodsImg.builder().newName(goodsImgDTO.getNewName()[i]).url(url).goods(goods)
						.thumbnail(false).build());
			}
		}
	}

}
