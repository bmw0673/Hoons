package com.hoons.service;

import java.util.Collections;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoons.domain.dto.UserDto;
import com.hoons.domain.entity.Authority;
import com.hoons.domain.entity.User;
import com.hoons.domain.entity.UserRole;
import com.hoons.domain.repository.UserRepository;
import com.hoons.exception.DuplicateMemberException;
import com.hoons.exception.NotFoundMemberException;
import com.hoons.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //회원가입
    @Transactional
    public void signup(UserDto userDto) {
    	System.out.println("회원가입Service실행");
    	
        if (userRepository.findByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 존재하는 아이디입니다.");
        }
        
        System.out.println("User 빌더실행");
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .activated(true)
                .build().addRole(UserRole.USER);
        
        System.out.println("디비저장");
        userRepository.save(user);

        //return UserDto.from(userRepository.save(user));
    }

	/*
	 * //userName을 기준으로 정보를 가져온다.
	 * 
	 * @Transactional(readOnly = true) public UserDto getUserWithAuthorities(String
	 * username) { return
	 * UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse
	 * (null)); }
	 * 
	 * //SecurityContext에 저장된 username의 정보만 가져온다.
	 * 
	 * @Transactional(readOnly = true) public UserDto getMyUserWithAuthorities() {
	 * return UserDto.from( SecurityUtil.getCurrentUsername()
	 * .flatMap(userRepository::findOneWithAuthoritiesByUsername) .orElseThrow(() ->
	 * new NotFoundMemberException("Member not found")) ); }
	 */
}
