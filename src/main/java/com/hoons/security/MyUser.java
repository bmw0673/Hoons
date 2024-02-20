package com.hoons.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.hoons.domain.entity.member.Member;

import lombok.Getter;

@Getter
public class MyUser extends User{

	private static final long serialVersionUID = 1L;
	
	private MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public MyUser(Member member, Set<SimpleGrantedAuthority> grnatedAuthority) {
		this(member.getEmail(), member.getPassword(), grnatedAuthority);
	}
	
	
}
