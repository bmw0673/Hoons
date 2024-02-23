package com.hoons.domain.entity.member;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	// 회원가입 날짜
	@CreationTimestamp
	private LocalDateTime joinDateTime;

	// 회워탈퇴 날짜
	private LocalDateTime quitDateTime;

	/*
	 * UserEntity에 대응하는 권한을 나타내는 테이블을 만듬 *UserEntity내부 테이블에 생성되지 않음!!! 중복을 허용하지 않고
	 * 순서가 상관없으므로 Set Collection을 이용해서 만듬
	 */
	@Builder.Default
	@Enumerated(EnumType.STRING) // 선언하지 않으면 DB에 저장시 ordinal(숫자)로 저장됨
	@CollectionTable(name = "member_role")
	@ElementCollection(fetch = FetchType.EAGER) // 1:n UserEntity에서만 접근가능한 내장테이블
	private Set<Role> memberRoles = new HashSet<>();

	/*
	 * 편의메서드 Service에서 회원가입시 Role을 부여하기 편하기 위한 편의메서드
	 */
	public Member addRole(Role role) {
		memberRoles.add(role);
		return this;
	}

}