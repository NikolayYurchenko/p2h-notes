package com.p2h.notes.config.security;

import com.p2h.notes.model.UserPrincipal;
import com.p2h.notes.model.UserResponse;
import com.p2h.notes.service.contract.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationManager {

    private final JwtResolver jwtResolver;

    private final UserService userService;

    protected Authentication resolveAuthentication(String token) {

        jwtResolver.validateToken(token);

        Jws<Claims> claims = jwtResolver.parseClaims(token);

        UserResponse userResponse = userService.whoami(claims.getBody().getSubject());

        return new UsernamePasswordAuthenticationToken(UserPrincipal.instance(userResponse.getUuid(), new ArrayList<>()), "password", new ArrayList<>());
    }
}
