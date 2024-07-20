package com.office.fourth.develop.serviceimpl;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret.key}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private Long jwtExpiration;

	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

	public String generateToken(String email, Map<String, Object> claims) {
		return createToken(claims, email);
	}

	private String createToken(Map<String, Object> claims, String employeeCode) {

		long now = System.currentTimeMillis();
		return Jwts.builder().setClaims(claims).setSubject(employeeCode).setIssuedAt(new Date(now)) // as issued time
				.setExpiration(new Date(now + jwtExpiration * 1000)).signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
