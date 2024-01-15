package com.hoons.domain.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hoons.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//username을 기준으로 User정보를 가져올때 권한 정보도 같이 가져오게 된다.
   @EntityGraph(attributePaths = "authorities")
   Optional<User> findOneWithAuthoritiesByUsername(String username);
}