package com.hoons.config.jwt;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.hoons.domain.entity.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenProvider {

	@Value("${jwt.issuer}")
	private String issuer;

	@Value("${jwt.secret_key}")
	private String secret_key;

	public String generateToken(Member user, Duration expiredAt) { 
		Date now = new Date(); 
		return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user); 
	}

	// JWT 토큰 생성 메서드 
	private String makeToken(Date expiry, Member user) { 
		Date now = new Date();
		
		return Jwts.builder()
				.setHeaderParam(Header.TYPE,Header.JWT_TYPE) // 헤더 typ : JWT
				// 내용 iss : ajufresh@gmail.com(propertise 파일에서 설정한 값) 
				.setIssuer(issuer)
				.setIssuedAt(now) // 내용 iat : 현재시간 
				.setExpiration(expiry) //내용 exp : expiry 멤버 변숫값 
				.setSubject(user.getEmail()) //내용 sub : 유저의 이메일 
				.claim("id",user.getId()) // 클레임 id : 유저 ID 
				//서명 : 비밀값과 함께 해시값을 HS256방식으로 암호화
				.signWith(SignatureAlgorithm.HS256,secret_key).compact();}

	// JWT 토큰 유효성 검증 메서드 
	public boolean validToken(String token) {
		try {
			System.out.println(">>>>>>>>>>>>>>>>>>>>");
			System.out.println("TokenProvide의 발급자 : " + issuer);
			System.out.println("TokenProvide의 비밀키 : " + secret_key);
			Jwts.parser()
			.setSigningKey(secret_key)
			.parseClaimsJws(token);
			return true;
		}catch (Exception e) { //복호화 과정에서 에러가 나면 유효하지 않은 토큰
			return false;
		}
	}
	
	//토큰 기반으로 인증 정보를 가져오는 메서드 
	public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }
	  
	  
	//토큰 기반으로 유저 ID를 가져오는 메서드 
	public Long getUserId(String token) {
		Claims claims = getClaims(token); 
		return claims.get("id", Long.class); 
	}

	private Claims getClaims(String token) {
		return Jwts.parser() // 클레임 조회
				.setSigningKey(secret_key)
				.parseClaimsJws(token)
				.getBody();
	}

}
