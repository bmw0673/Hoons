package com.hoons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

	private final UserDetailsService userservice;
	
	
	  //스프링 시큐리티 기능 비활성화
	  @Bean WebSecurityCustomizer configure() { return (web) -> web.ignoring();}
	  //.requestMatchers(toH2console()) .regexMatchers("/static/**"); }
	 
	
	//특정 Http 요청에 대한 웹 기반 보안 구성
	@Bean
	SecurityFilterChain fillChain(HttpSecurity http) throws Exception{
		http
			.csrf(csrf->csrf.disable()) //csrf 비활성화
			.authorizeHttpRequests(authorize->authorize //인증, 인가 설정
				.requestMatchers("/css/**","/images/**","/js/**","/"
						,"/login","/signup","/user").permitAll()
				.anyRequest().authenticated())//나머지는 인증(로그인)해야해요
			.formLogin(formLogin -> formLogin //폼 기반 로그인 설정
				.loginPage("/login") //로그인 페이지 경로
				.loginProcessingUrl("/login")
				.usernameParameter("email") //default=username--form
				.passwordParameter("password") //default=password--form
				.permitAll()
				)
			.logout(logout -> logout  //로그아웃 설정
				.logoutSuccessUrl("/login") //로그아웃 이후 이동할 경로
			 	.invalidateHttpSession(true) //로그아웃 이후 세션 전체 삭제여부
			 	.permitAll()
			 	);
		return http.build();
		
	}
	
	//인증 관리자 관련 설정
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder
			, UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userservice)
				.passwordEncoder(bCryptPasswordEncoder)
				.and()
				.build();
	}
	
	//패스워드 인코더로 사용할 빈 등록
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(14);
	}
}
