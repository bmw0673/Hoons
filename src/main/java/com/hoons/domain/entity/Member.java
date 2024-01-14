package com.hoons.domain.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member implements UserDetails{

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence")
	@SequenceGenerator(name = "member_sequence", sequenceName = "member_sequence", allocationSize = 1)
	@Column(name = "member_id")
	@Id
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String password;

	@Builder
	public Member(String email, String password, String auth) {
		this.email=email;
		this.password=password;
	}
	
	//권환 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}
	
	//사용자의 패스워드를 반환
	@Override
	public String getPassword() {
		return password;
	}
	
	//사용자의 id를 반환(고유한 값)
	@Override
	public String getUsername() {
		return email;
	}

	//계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		//만료되었는지 확인하는 로직
		return true; //true -> 만료되지 않았음
	}

	//계정 장금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		//계정 잠금되었는지 확인하는 로직
		return true; //true -> 잠금되지 않았음
	}

	//패스워드의 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
		//패스워드가 만료되었는지 확인하는 로직
		return true; //true -> 만료되지 않았음
	}

	//계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
		//계정이 사용 가능한지 확인하는 로직
		return true; //true -> 사용 가능
	}
	
	
}
