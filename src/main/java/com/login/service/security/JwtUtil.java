package com.login.service.security;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import static java.util.Collections.emptyList;

import java.io.IOException;

public class JwtUtil {
	 
	
	private static final String KEYSECRET = "M@cr@n";

	static void addAuthentication(HttpServletResponse res, String nombre) throws IOException {
		String token = Jwts.builder()
				.setSubject(nombre)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000 ))
				.signWith(SignatureAlgorithm.HS512, KEYSECRET)
				.compact();
		res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(
					"{\"" + "Authorization" + "\":\"" + "Bearer "+token + "\"}"
					);
	}
	
	public static String createToken(String nombre) {
		String token = Jwts.builder()
				.setSubject(nombre)
				.setExpiration(new Date(System.currentTimeMillis() + 1800000))
				.signWith(SignatureAlgorithm.HS512, "M@cr@nVerify")
				.compact();
		return token;
	}
	
	public static String createTokenforFirstLogin(String nombre) {
		String token = Jwts.builder()
				.setSubject(nombre)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000 ))
				.signWith(SignatureAlgorithm.HS512, KEYSECRET)
				.compact();
		return token;
	}
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			String nombre = Jwts.parser()
					.setSigningKey("M@cr@n")
					.parseClaimsJws(token.replace("Bearer", ""))
					.getBody()
					.getSubject();
			return nombre != null ?
					new UsernamePasswordAuthenticationToken(nombre, null, emptyList()) :
						null;
		}
		return null;
	}
	
}
