package com.suportehe.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suportehe.model.Usuario;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JwtLoginFilter(String url, AuthenticationManager authenticationManager) {
		
		super(new AntPathRequestMatcher(url));
		
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		
		return getAuthenticationManager().
				authenticate(new UsernamePasswordAuthenticationToken(
						user.getLogin(), user.getSenha()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	
		try {
			new JwtTokenAutenticacaoService().addAuthentication(response, authResult.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		if(failed instanceof BadCredentialsException) {
			response.getWriter().write("Usuário ou senha não encontrado. Tente Novamente!");
		} else {
			response.getWriter().write("Ocorreu um erro ao logar: " + failed.getMessage());
		}
		//super.unsuccessfulAuthentication(request, response, failed);
	}
}
