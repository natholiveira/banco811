package com.santander.banco811.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.santander.banco811.security.JWTAuthenticateFilter.PASSWORD_TOKEN;

public class JWTValidateFilter extends BasicAuthenticationFilter {

    public JWTValidateFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
       var authorizationToken = request.getHeader(AUTHORIZATION);

       if (authorizationToken == null) {
           chain.doFilter(request, response);
           return;
       }

       if (!authorizationToken.startsWith(TOKEN_PREFIX)) {
          chain.doFilter(request, response);
          return;
       }

       var token = authorizationToken.replace(TOKEN_PREFIX, "");

       var authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        var usuario = JWT.require(Algorithm.HMAC512(PASSWORD_TOKEN))
                .build()
                .verify(token)
                .getSubject();

        return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
    }

    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
