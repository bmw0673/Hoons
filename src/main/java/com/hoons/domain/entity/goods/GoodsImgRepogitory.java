package com.hoons.domain.entity.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsImgRepogitory extends JpaRepository<GoodsImg, Long>{


	boolean existsByNewName(String newName);

}
