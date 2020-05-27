package com.login.service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.service.models.Local;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;


public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest req, HttpServletResponse res)
					throws AuthenticationException, IOException, ServletException {
		InputStream body = req.getInputStream();
		Local local = new ObjectMapper().readValue(body, Local.class);
		System.out.println("Nombre:"+local.getNombre() + "Pass:" + local.getPassword());
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						local.getNombre(),
						local.getPassword(),
						Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest req,
			HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		JwtUtil.addAuthentication(res, auth.getName());
	}
}