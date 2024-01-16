package com.hoons.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoons.domain.dto.LoginDto;
import com.hoons.domain.dto.TokenDto;
import com.hoons.jwt.JwtFilter;
import com.hoons.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
	
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        System.out.println("userdetails 실행");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
       
        System.out.println("시큐리티 context 저장 실행");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("jwt 토큰 생성");
        String jwt = tokenProvider.createToken(authentication);

        System.out.println("헤더에 토큰넣기");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}