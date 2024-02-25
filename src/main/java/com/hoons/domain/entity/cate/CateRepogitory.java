package com.hoons.domain.entity.cate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CateRepogitory extends JpaRepository<Category, Long>{

	List<Category> findByParent(Category parent);

}
