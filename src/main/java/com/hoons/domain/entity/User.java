package com.hoons.domain.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

   @Id
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;

   @Column(name = "username", length = 50, unique = true)
   private String username;

   @Column(name = "password", length = 100)
   private String password;

   @Column(name = "activated")
   private boolean activated;

    /*
	 * UserEntity에 대응하는 권한을 나타내는 테이블을 만듬 *UserEntity내부 테이블에 생성되지 않음!!!
	 * 중복을 허용하지 않고 순서가 상관없으므로 Set Collection을 이용해서 만듬
	 */
	@Builder.Default
	@Enumerated(EnumType.STRING)  //선언하지 않으면 DB에 저장시 ordinal(숫자)로 저장됨
	@CollectionTable(name = "role")
	@ElementCollection(fetch = FetchType.EAGER)//1:n UserEntity에서만 접근가능한 내장테이블
	private Set<UserRole> userRoles = new HashSet<>();
	
	
	/*
	 * 편의메서드
	 * Service에서 회원가입시 Role을 부여하기 편하기 위한 편의메서드
	 */
	public User addRole(UserRole role) {
		userRoles.add(role);
		return this;
	}
}
