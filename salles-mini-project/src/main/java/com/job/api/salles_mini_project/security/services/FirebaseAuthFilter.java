package com.job.api.salles_mini_project.security.services;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseToken;
import com.job.api.salles_mini_project.model.entities.Usuario;
import com.job.api.salles_mini_project.service.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirebaseAuthFilter extends OncePerRequestFilter {
	
		@Autowired
	    private FirebaseTokenServices firebaseTokenService;
	    
		@Autowired
		private UsuarioService usuarioService;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain) throws ServletException, IOException {

	        String authorization = request.getHeader("Authorization");

	        if (authorization != null && authorization.startsWith("Bearer ")) {
	            try {
	                String idToken = authorization.substring(7);

	                FirebaseToken decodedToken = firebaseTokenService.verificarToken(idToken);

	                String firebaseUid = decodedToken.getUid();
	                String nome = decodedToken.getName();
	                String email = decodedToken.getEmail();

	                Usuario usuarioLogado = usuarioService.buscarOuCriarUsuario(firebaseUid, nome, email);

	                UsernamePasswordAuthenticationToken authentication =
	                        new UsernamePasswordAuthenticationToken(
	                                usuarioLogado,
	                                null,
	                                Collections.emptyList()
	                        );

	                SecurityContextHolder.getContext().setAuthentication(authentication);

	            } catch (Exception e) {
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                response.setContentType("application/json");
	                response.getWriter().write("{\"erro\":\"Token inválido ou expirado\"}");
	                return;
	            }
	        }

	        filterChain.doFilter(request, response);
	    }
}
