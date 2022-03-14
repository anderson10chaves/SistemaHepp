package com.suportehe.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.suportehe.ApplicationContextLoad;
import com.suportehe.model.Usuario;
import com.suportehe.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
@Component
public class JwtTokenAutenticacaoService {
	
	/*Token de validade do token de 15 dias*/
	private static final long EXPIRATION_TIME = 1296000000;
	

	private static final String SECRET = "hepp@hepp";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		String JWT = Jwts.builder() 
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PREFIX + " " + JWT;
		
		response.addHeader(HEADER_STRING, token);
		
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String token = request.getHeader(HEADER_STRING);
		
		try {
		
		if(token != null) {
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			String user = Jwts.parser().
					setSigningKey(SECRET)
					.parseClaimsJws(tokenLimpo)
					.getBody().getSubject();
			
			if(user != null) {
				
				Usuario usuario = ApplicationContextLoad.getApplicationContext().
						getBean(UsuarioRepository.class).findByLogin(user);
				
				if(usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(),
							usuario.getAuthorities());
				}
				
			}
		}
		
		} catch (SignatureException e) {
			response.getWriter().write("Token esta inválido, realize um novo login.");
			e.printStackTrace();
			
		} catch (Exception e) {
			response.getWriter().write("Token esta expirado, realize novo login.");
			e.printStackTrace();
			
		} finally {
			liberacaoCors(response);
		}
		
		return null;
	}
	
	/*Liberação do Cors erro de end-points*/
	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Acesss-Control-Allow-Origin") == null) {
			response.addHeader("Acesss-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Acesss-Control-Allow-Headers") == null) {
			response.addHeader("Acesss-Control-Allow-Headers", "*");
		}
		
		if (response.getHeader("Acesss-Control-Request-Headers") == null) {
			response.addHeader("Acesss-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Acesss-Control-Allow-Methods") == null) {
			response.addHeader("Acesss-Control-Allow-Methods", "*");
		}
	}

}