package com.hoons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hoons.config.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

	private final TokenProvider tokenProvider;

	// 스프링 시큐리티 기능 비활성화
	@Bean
	WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
				// .requestMatchers(toH2console())
				.requestMatchers("/img/**", "/css/**", "/js/**");
	}

	// 특정 Http 요청에 대한 웹 기반 보안 구성
	@Bean
	SecurityFilterChain fillChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()) // csrf 비활성화
				.httpBasic(httpBasic -> httpBasic.disable())
				.formLogin(formLogin -> formLogin.disable())
				.logout(logout -> logout.disable());

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		//헤더를 확인할 커스텀 필터 추가
		http.addFilterBefore(tokenAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
		
		// 토큰 재발급 URL은 인증 없이 접근 가능하도록 설정. 나머지는 API URL은 인증 필요
		http.authorizeHttpRequests(authorize -> authorize // 인증, 인가 설정
				.requestMatchers("/api/token").permitAll()
				.requestMatchers("/api/**").authenticated()
				.requestMatchers("/admin/**").authenticated()
				.anyRequest()
				.permitAll());


		http
			.logout(logout -> logout.logoutSuccessUrl("/login"));
		
		//  /api로 시작하는 url인 경우 인증실패시 401 상태코드를 반환하도록 예외 처리
		  http .exceptionHandling(exceptionHandling -> exceptionHandling
		  .defaultAuthenticationEntryPointFor( new
		  HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), new
		  AntPathRequestMatcher("/api/**")));
		 
		 

		return http.build();

	}

	@Bean
	TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter(tokenProvider);
	}


	// 패스워드 인코더로 사용할 빈 등록
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(14);
	}
}
