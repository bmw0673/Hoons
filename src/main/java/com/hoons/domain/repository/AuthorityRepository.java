package com.hoons.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoons.domain.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
