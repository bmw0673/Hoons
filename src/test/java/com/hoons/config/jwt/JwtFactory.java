package com.hoons.config.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.util.Date;
import java.util.Map;


import static java.util.Collections.emptyMap;

@Getter
public class JwtFactory {

	 private String subject = "test@email.com";

	    private Date issuedAt = new Date();

	    private Date expiration = new Date(new Date().getTime() + Duration.ofDays(14).toMillis());

	    private Map<String, Object> claims = emptyMap();

	    @Builder
	    public JwtFactory(String subject, Date issuedAt, Date expiration,
	                   Map<String, Object> claims) {
	        this.subject = subject != null ? subject : this.subject;
	        this.issuedAt = issuedAt != null ? issuedAt : this.issuedAt;
	        this.expiration = expiration != null ? expiration : this.expiration;
	        this.claims = claims != null ? claims : this.claims;
	    }

	    public static JwtFactory withDefaultValues() {
	        return JwtFactory.builder().build();
	    }

    public String createToken(String issuer, String secret_key) {
        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }
}
