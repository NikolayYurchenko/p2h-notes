package com.p2h.notes.config.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final String AUTH_HEADER = "Authorization";

    private final AuthenticationManager authenticationManager;

    public JwtTokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(AUTH_HEADER);

        try {

            if (jwtToken != null) {

                Authentication authentication = authenticationManager.resolveAuthentication(jwtToken);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (AuthenticationCredentialsNotFoundException e) {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }
}