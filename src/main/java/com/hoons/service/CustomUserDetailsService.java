package com.hoons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hoons.domain.entity.User;
import com.hoons.domain.repository.UserRepository;


import java.util.Set;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// 로그인시 DB에서 유저정보와 권한정보를 가져오게 됩니다.
	// 해당 정보를 기준으로 userdetails.User 개체를 생성해서 리턴합니다.
	@Override
	public UserDetails loadUserByUsername(String username) {

		/*
		 * 반환 타입이 Optional 일시 변경 해주어야 함. Optional 경우 NULL값의 예외처리를 좀 더 효과적으로 할 수 있음.
		 * Optional 일시 아래코드 참조 repository.findByEmail(email).orElseThrow(()->new
		 * UsernameNotFoundException("존재하지 않는 유저")); NULL의 경우에서의 예외처리를 해주어야 함.
		 */
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저"));
		
		if(!user.isActivated()) {
			throw new RuntimeException(username + "은 활성화되어 있지 않습니다.");
		}
		
		/*
		 * DB에 해당하는 사용자가 있으면 사용자의 ROLE(권한)을 객체에 넣어 반환. Spring.io Security 를 참고하여 ROLE을
		 * 반환타입에 맞게 변환해준다.
		 */
		Set<SimpleGrantedAuthority> grnatedAuthority = user.getUserRoles().stream()
				.map((myRole) -> new SimpleGrantedAuthority("ROLE_" + myRole.name())).collect(Collectors.toSet());

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grnatedAuthority);
	}
}